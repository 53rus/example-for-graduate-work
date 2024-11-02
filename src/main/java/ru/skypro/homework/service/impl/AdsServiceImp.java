package ru.skypro.homework.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdAdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdsServiceImp implements AdsService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    /**
     * Получает все объявления
     *
     * @return DTO, содержащий список всех объявлений.
     */
    @Override
    public Ads getAllAds() {

        List<AdDTO> adList = adRepository.findAll().stream()
                .map(AdMapper::mapAdToadDTO)
                .collect(Collectors.toList());
        return new Ads(adRepository.findAll().size(), adList);
    }

    /**
     * Создает новое объявление.
     *
     * @param createOrUpdateAd DTO, содержащий данные объявления.
     * @param image            картинка, связанный с объявлением.
     * @param authentication   аутентификационная информация пользователя, создающего объявление.
     * @return DTO, представляющий созданное объявление.
     * @throws IOException Если при чтении файла изображения возникает ошибка.
     */
    @Override
    public AdDTO addAd(CreateOrUpdateAd createOrUpdateAd,
                       MultipartFile image,
                       Authentication authentication) throws IOException {

        User user = userService.getUser(authentication.getName());
        Ad ad = AdMapper.mapCreateOrUpdateAdToAd(createOrUpdateAd);
        ad.setUser(user);
        ad.setData(image.getBytes());
        ad.setImage(image.getOriginalFilename());
        return AdMapper.mapAdToadDTO(adRepository.save(ad));
    }

    /**
     * Получает расширенную версию объявления.
     *
     * @param id Идентификатор объявления, которое требуется получить.
     * @return DTO, представляющий расширенное объявление.
     */

    @Override
    public ExtendedAd getAds(Integer id) {
        Ad ad = adRepository.findByPk(id).orElseThrow(AdAdNotFoundException::new);;
        if (ad != null) {
            return AdMapper.mapAdToExtendedAd(ad);
        }
        return null;
    }

    /**
     * Метод удаления объявления
     *
     * @param id             Идентификатор объявления, которое требуется удалить.
     * @param authentication аутентификационная информация пользователя, удаляющего объявление
     * @return статус выполнения
     */
    @Override
    @Transactional
    public HttpStatus deleteAd(Integer id,
                               Authentication authentication) {
        try {
            User user = userService.getUser(authentication.getName());
            Ad ad = adRepository.findByPk(id).orElseThrow(AdAdNotFoundException::new);;

            Role role = user.getRole();
            boolean isAuthorAd = (ad.getUser().equals(user));
            boolean permit = isAuthorAd || role == Role.ADMIN;

            if (permit) {
                adRepository.delete(ad);
                return HttpStatus.OK;
            } else {
                return HttpStatus.FORBIDDEN;
            }
        } catch (UsernameNotFoundException e) {
            return HttpStatus.NO_CONTENT;
        } catch (AdAdNotFoundException e) {
            return HttpStatus.NOT_FOUND;
        }

    }

    /**
     * Обновляет объявления.
     *
     * @param id             Идентификатор объявления, которое требуется обновить.
     * @param ad             DTO, содержащий обновленные данные объявления.
     * @param authentication аутентификационная информация пользователя, удаляющего объявление
     * @return DTO, представляющий обновленное объявление.
     */
    @Override
    public ResponseEntity<AdDTO> updateAd(Integer id,
                                          CreateOrUpdateAd ad,
                                          Authentication authentication) {

        Ad foundedAd = adRepository.findByPk(id).orElseThrow(AdAdNotFoundException::new);
        User user = userService.getUser(authentication.getName());

        Role role = user.getRole();
        boolean isAuthorAd = (foundedAd.getUser().equals(user));
        boolean permit = isAuthorAd || role == Role.ADMIN;

        if (permit) {
            Ad adFromAdDTO = AdMapper.mapCreateOrUpdateAdToAd(ad);
            foundedAd.setPrice(adFromAdDTO.getPrice());
            foundedAd.setDescription(adFromAdDTO.getDescription());
            foundedAd.setTitle(adFromAdDTO.getTitle());

            Ad updateAd = adRepository.save(foundedAd);

            return ResponseEntity.ok(AdMapper.mapAdToadDTO(updateAd));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Извлекает все объявления, созданные авторизованным пользователем.
     *
     * @param authentication аутентификационная информация пользователя
     * @return DTO, содержащий список всех объявлений, созданных пользователем.
     */
    @Override
    public Ads getAdsMe(Authentication authentication) {
        Integer userId = userService.getUser(authentication.getName()).getId();
        List<AdDTO> myAd = adRepository.findAll().stream()
                .filter(ad -> ad.getUser().getId().equals(userId))
                .map(AdMapper::mapAdToadDTO)
                .collect(Collectors.toList());
        return new Ads(myAd.size(), myAd);
    }

    /**
     * Обновляет изображение объявления
     * @param id Идентификатор объявления, которое необходимо обновить.
     * @param image Новый файл изображения для объявления.
     * @param authentication аутентификационная информация пользователя
     * @return Массив байтов, представляющий обновленные данные изображения.
     * @throws IOException Если при чтении файла изображения возникает ошибка.
     */

    @Override
    public ResponseEntity<byte[]> updateImage(Integer id,
                                              MultipartFile image,
                                              Authentication authentication) throws IOException {
        User user = userService.getUser(authentication.getName());
        Ad ad = adRepository.findById(id).orElseThrow(AdAdNotFoundException::new);

        Role role = user.getRole();
        boolean isAuthorAd = (ad.getUser().equals(user));
        boolean permit = isAuthorAd || role == Role.ADMIN;

        if (permit) {
            ad.setData(image.getBytes());
            ad.setImage("/images" + ad.getPk());
            adRepository.save(ad);
            return ResponseEntity.ok(ad.getData());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Получает данные изображения для объявления.
     * @param imageId Идентификатор рекламного изображения, которое нужно получить.
     * @return Массив байтов, представляющий обновленные данные изображения.
     * @throws IOException Если при чтении файла изображения возникает ошибка.
     */
    @Override
    public byte[] getImage(Integer imageId) throws IOException {
        return adRepository.findByPk(imageId).get().getData();
    }
}
