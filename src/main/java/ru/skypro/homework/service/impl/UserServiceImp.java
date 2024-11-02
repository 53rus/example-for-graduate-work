package ru.skypro.homework.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Устанавливает пароль для пользователя
     *
     * @param newPassword    Информауия о паролях
     * @param authentication аутентификационная информация пользователя
     * @return статус выполнения
     */
    @Override
    public ResponseEntity setPassword(NewPassword newPassword,
                                      Authentication authentication) {

        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User saveUser = user.get();
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), saveUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            saveUser.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            userRepository.save(saveUser);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Получает объект UserDto, представляющий вошедшего в систему пользователя.
     *
     * @param authentication аутентификационная информация пользователя
     * @return Объект UserDto, представляющий вошедшего в систему пользователя.
     * @throws UserNotFoundException когда пользователь не найден
     */
    @Override
    public UserDTO getLogUser(Authentication authentication) {

        return UserMapper.mapUserToUserDTO(userRepository.findByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new));
    }

    /**
     * Обновляет информацию о пользователе.
     *
     * @param updateUser     Объект UpdateUserDto, содержащий обновленную информацию о пользователе.
     * @param authentication Аутентификация текущего пользователя.
     * @throws UserNotFoundException когда пользователь не найден
     * @return Объект UpdateUser, представляющий обновленную информацию о пользователе.
     */
    @Override
    public UpdateUser updateUser(UpdateUser updateUser,
                                 Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
        return UserMapper.mapUserToUpdateUser(user);
    }

    /**
     *
     Обновляет аватар пользователя
     * @param image Объект MultipartFile, представляющий новое изображение аватара пользователя.
     * @param authentication Аутентификация текущего пользователя.
     * @throws IOException Если при чтении данных изображения возникает ошибка ввода-вывода.
     * @throws UserNotFoundException Когда пользователь не найден
     */

    @Override
    public void updateUserImage(MultipartFile image,
                                Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new);

        user.setData(image.getBytes());
        user.setImage("/avatars/" + user.getId());

        userRepository.save(user);
    }

    /**
     *Получает данные изображения пользователя с указанным идентификатором.
     * @param id Идентификатор пользователя
     * @return Массив байтов, представляющий данные изображения.
     * @throws IOException Если при чтении данных изображения возникает ошибка ввода-вывода.
     */

    @Override
    public byte[] getImage(Integer id) throws IOException {
        return userRepository.findById(id).get().getData();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
    }
}
