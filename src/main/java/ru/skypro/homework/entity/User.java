package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "user_avatar")
    private String image;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;
}
