package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    private Integer pk;
    private String text;
    private Long createAt;

    private Ad ad;
    private User user;




}
