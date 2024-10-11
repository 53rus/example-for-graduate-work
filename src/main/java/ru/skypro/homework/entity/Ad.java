package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Ad {

    @Id
    private Integer pk;

    private User user;
    private Integer price;
    private String title;
    private String description;
    private String image;

}
