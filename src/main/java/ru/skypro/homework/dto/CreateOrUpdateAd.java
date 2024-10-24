package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAd {
    /**
     * Заголовок объявления
     */
    @Size(min = 4, max = 32)
    private String title;
    /**
     * Цена объявления
     */
    @Min(0)
    @Max(10_000_000)
    private Integer price;
    /**
     * Описание объявления
     */
    @Size(min = 8, max = 64)
    private String description;
}
