package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.CommentListMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CommentService {
    private final UserRepository userRepository;

    private final CommentMapper commentMapper;
    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;

    private final CommentListMapper commentListMapper;
    private final Logger logger = Logger.getLogger("CommentServiceLogger");

    public CommentService(UserRepository userRepository, CommentMapper commentMapper, AdsRepository adsRepository, CommentRepository commentRepository, CommentListMapper commentListMapper) {
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
        this.adsRepository = adsRepository;
        this.commentRepository = commentRepository;
        this.commentListMapper = commentListMapper;
    }


    public ResponseWrapperCommentDto getCommentsByAdId(Integer id) {
        Optional<Ads> ad = adsRepository.findById(id);
        if (ad.isPresent()) {
            return commentListMapper.toResponseWrapperCommentDto(ad.get());
        } else {
            logger.info("Ad with id=" + id + " not found");
            return null;
        }
    }

    public CommentDto addComment(Integer id, CreateCommentDto createCommentDto) {

        Comment comment = commentMapper.toComment(createCommentDto);
        Integer userId = 1;

        Optional<Ads> ad = adsRepository.findById(id);
        if (ad.isEmpty()) {
            logger.info("Ad with id=" + id + " not found");
            return null;
        }

        Optional<User> author = userRepository.findById(userId);
        if (author.isEmpty()) {
            logger.info("User with id=" + userId + " not found");
            return null;
        }

        comment.setAd(ad.get());
        comment.setAuthor(author.get());
        comment.setCreatedAt(System.currentTimeMillis());
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    public CommentDto updateComment(Integer commentId, CommentDto commentDto) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            commentMapper.updateComment(commentDto, comment.get());
            return commentMapper.toDto(commentRepository.save(comment.get()));
        } else {
            logger.info("Comment with id=" + commentId + " not found");
            return null;
        }
    }


}
