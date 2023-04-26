package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(CommentId.class)
public class Comment {

    @Id

    private Integer adId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer commentId;

    private Integer author;

    private Long createdAt;

    private String text;


}
