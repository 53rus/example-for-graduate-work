package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
public class NewPassword {
    /**
     * Текущий пароль
     */
    @Size(min = 8, max = 16)
    private String currentPassword;
    /**
     * Новый пароль
     */
    @Size(min = 8, max = 16)
    private String newPassword;

}
