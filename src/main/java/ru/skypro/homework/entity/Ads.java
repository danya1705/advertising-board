package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    private int price ;

    private String title;

    private String description;

    @OneToOne
    @JoinColumn(name = "image")
    private Image image;


}
