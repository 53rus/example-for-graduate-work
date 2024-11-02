package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private LocalDateTime createAt;
    private String text;

    @ManyToOne
    @JoinColumn(name ="ad_id")
    private Ad ad;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;


}
