package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.util.List;

public interface AdsService {
    Ads getAllAds();

    AdDTO addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image);

    ExtendedAd getAds(Integer id);

    void deleteAd(Integer id);

    AdDTO updateAd(Integer id, CreateOrUpdateAd ad);

    Ads getAdsMe();

    byte[] updateImage(Integer id, MultipartFile image);

}
