package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private String title;
    private String description;
    private Integer price;
    @Column(name = "ad_image")
    private String image;
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
