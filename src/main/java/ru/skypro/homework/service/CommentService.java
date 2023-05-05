package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.repository.CommentRepository;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public List<Comment> getCommentsAds(Integer id) {
        return null;
    }

    public CommentDto addCommentAds(Integer id, CommentDto commentDto) {
        return null;
    }

    public CommentDto deleteCommentAds(Integer adId, Integer commentId) {
        return null;
    }

    public CommentDto updateCommentAds(Integer adId, Integer commentId, CommentDto commentDto) {
        return null;
    }


}
