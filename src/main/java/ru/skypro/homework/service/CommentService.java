package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
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


    public ResponseWrapperCommentDto getCommentsAdById(Integer id) {
        Ads ad = adsRepository.findById(id).get();
        return commentListMapper.toResponseWrapperCommentDto(ad);
    }

    public CommentDto addCommentAd(Integer id, CreateCommentDto createCommentDto) {
        Comment comment = commentMapper.toComment(createCommentDto);
        Ads ad = adsRepository.findById(id).get();
        comment.setAd(ad);
        comment.setAuthor(userRepository.findById(1).get());
        comment.setCreatedAt(System.currentTimeMillis());
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    public CommentDto deleteCommentAds(Integer adId, Integer commentId) {
        Optional<Comment> optionalComment = commentRepository.findCommentByAd_PkAndCommentId(adId, commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            commentRepository.delete(comment);
            return commentMapper.toDto(comment);
        }else
            {
                return null;
            }

    }

    public CommentDto updateCommentAds(Integer adId, Integer commentId, CommentDto commentDto) {

        return null;
    }


}
