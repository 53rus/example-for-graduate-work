package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;
import java.util.List;

public interface AdsService {
    Ads getAllAds();

    AdDTO addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, Authentication authentication) throws IOException;

    ExtendedAd getAds(Integer id);

    @Transactional
    HttpStatus deleteAd(Integer id, Authentication authentication);

    ResponseEntity<AdDTO> updateAd(Integer id, CreateOrUpdateAd ad, Authentication authentication);

    Ads getAdsMe(Authentication authentication);

   ResponseEntity<byte[]>updateImage(Integer id, MultipartFile image, Authentication authentication) throws IOException;

    byte[] getImage(Integer imageId) throws IOException;

}
