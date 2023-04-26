package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(CommentId.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adId;

    @Id
    private Integer commentId;

    private Integer author;

    private Long createdAt;

    private String text;


}
