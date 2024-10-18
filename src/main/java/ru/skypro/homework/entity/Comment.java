package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Long createAt;
    private String text;

    @ManyToOne
    @JoinColumn(name ="ad_id")
    private Ad ad;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;


}
