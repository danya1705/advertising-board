package ru.skypro.homework.entity;

import java.io.Serializable;
import java.util.Objects;

public class CommentId implements Serializable {

    private Integer adId;

    private Integer commentId;

    public CommentId(Integer adId, Integer commentId) {
        this.adId = adId;
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId1 = (CommentId) o;
        return Objects.equals(adId, commentId1.adId) && Objects.equals(commentId, commentId1.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adId, commentId);
    }
}
