package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
@NoArgsConstructor
@Entity
public class User {

    private Integer id;

    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String image;
}
