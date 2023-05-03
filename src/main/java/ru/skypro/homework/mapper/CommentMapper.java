package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CommentMapper {

    @Autowired
    private UserService userService;
    @Autowired
    private AdsService adsService;

    @Mapping(source = "commentId", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image.url", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    abstract CommentDto toDto(Comment comment);

    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    abstract void updateComment(CommentDto commentDto, @MappingTarget Comment comment);

    Comment toComment(CommentDto commentDto, Integer adsId) {
        User author = userService.getUserById(commentDto.getAuthor());
        Ads ad = adsService.getAdById(adsId);
        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setAuthor(author);
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(commentDto.getText());
        return comment;
    }

}
