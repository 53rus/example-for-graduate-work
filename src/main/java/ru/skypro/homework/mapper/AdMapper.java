package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ad;

@Component
public class AdMapper {
    public static Ad mapAdDTOToAd (AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setPk(adDTO.getPk());
        ad.setTitle(adDTO.getTitle());
        ad.setPrice(adDTO.getPrice());
        ad.setImage(adDTO.getImage());
        return ad;
    }

    public static AdDTO mapAdToadDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setAuthor(ad.getUser().getId());
        adDTO.setImage(ad.getImage());
        adDTO.setPk(ad.getPk());
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());
        return adDTO;
    }

    public static Ad mapCreateOrUpdateAdToAd(CreateOrUpdateAd adDTO) {
        Ad ad = new Ad();
        ad.setTitle(adDTO.getTitle());
        ad.setPrice(adDTO.getPrice());
        ad.setDescription(adDTO.getDescription());
        return ad;
    }

    public static CreateOrUpdateAd mapAdToCreateOrUpdateAd(Ad ad) {
        CreateOrUpdateAd adDTO = new CreateOrUpdateAd();
        adDTO.setTitle(ad.getTitle());
        adDTO.setPrice(ad.getPrice());
        adDTO.setDescription(ad.getDescription());
        return adDTO;
    }

    public static ExtendedAd mapAdToExtendedAd(Ad ad) {
        ExtendedAd adDTO = new ExtendedAd();
        adDTO.setPk(ad.getPk());
        adDTO.setAuthorFirstName(ad.getUser().getFirstName());
        adDTO.setAuthorLastName(ad.getUser().getLastName());
        adDTO.setDescription(ad.getDescription());
        adDTO.setEmail(ad.getUser().getEmail());
        adDTO.setImage(ad.getImage());
        adDTO.setPhone(ad.getUser().getPhone());
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());
        return adDTO;
    }
}
