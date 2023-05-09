package ru.skypro.homework.entity;

import lombok.Data;
import lombok.ToString;
import ru.skypro.homework.dto.AdsDto;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString
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

    @OneToMany(mappedBy = "ad")
    private List<Comment> commentsList;

}
