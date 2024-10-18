package ru.skypro.homework.dto;

import lombok.Data;

import java.util.PrimitiveIterator;

@Data
public class UserDTO {
    /**
     * Id пользователя
     */
    private Integer id;
    /**
     * Логин пользователя
     */
    private String email;
    /**
     * Имя пользователя
     */
    private String firstName;
    /**
     * Фамилия пользователя
     */
    private String lastName;
    /**
     * Телефон пользователя
     */
    private String phone;
    /**
     * Роль пользователя
     */
    private Role role;
    /**
     * Ссылка на аватар пользователя
     */
    private String image;

}
