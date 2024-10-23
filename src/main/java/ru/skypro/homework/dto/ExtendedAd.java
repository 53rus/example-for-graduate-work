package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.security.core.parameters.P;

@Data
public class ExtendedAd {
    /**
     * Id объявления
     */
    private Integer pk;
    /**
     * Имя автора объявления
     */
    private String authorFirstName;
    /**
     * Фамилия автора объявления
     */
    private String authorLastName;
    /**
     * Описание объявления
     */
    private String description;
    /**
     * Логин автора объявления
     */
    private String email;
    /**
     * Ссылка на картинку объявления
     */
    private String image;
    /**
     * Телефон автора объявления
     */
    private String phone;
    /**
     * Цена объявления
     */
    private Integer price;
    /**
     * Заголовок объявления
     */
    private String title;



}
