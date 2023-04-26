package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private Integer author;

    private int price ;

    private String firstName;

    private String title;

    private String description;

    private String image;


}
