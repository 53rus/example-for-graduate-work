package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Ads {
    /**
     * Общее количество объявлений
     */
    private Integer count;
    /**
     * Список всех объявлений
     */
    private List<AdDTO> results;

}
