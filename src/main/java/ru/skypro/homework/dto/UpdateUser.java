package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUser {
    /**
     * Имя пользователя
     */
    @Size(min = 3, max = 10)
    private String firstName;
    /**
     * Фамилия пользователя
     */
    @Size(min = 3, max = 10)
    private String lastName;
    /**
     * Телефон пользователя
     */
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
