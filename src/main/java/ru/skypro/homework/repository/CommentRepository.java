package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByAd_Pk(Integer id);

    Optional<Comment> findCommentByAd_PkAndCommentId(Integer adId, Integer commentId);



 }
