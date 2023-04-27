package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(CommentId.class)
public class Comment {

    @Id
    @ManyToOne
    @JoinColumn(name = "adId")
    private Ads ad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    private Long createdAt;
    private String text;
}
