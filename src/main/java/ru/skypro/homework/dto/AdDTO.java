package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDTO {
    /**
     * Id автора объявления
     */
    private Integer author;
    /**
     * Ссылка на картинку объявления
     */
    private String image;
    /**
     * Id объявления
     */
    private Integer pk;
    /**
     * Цена объявления
     */
    private int price;
    /**
     * Заголовок объявления
     */
    private String title;
}
