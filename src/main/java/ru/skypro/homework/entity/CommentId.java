package ru.skypro.homework.entity;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class CommentId implements Serializable {

    private Ads ad;
    private Integer commentId;

}
