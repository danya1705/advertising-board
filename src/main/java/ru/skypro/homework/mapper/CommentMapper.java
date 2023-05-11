package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.entity.Comment;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {

    @Mapping(source = "commentId", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage",
            expression = "java(\"/img/\" + java.nio.file.Path.of(comment.getAuthor().getImage().getFilePath()).getFileName().toString())")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    CommentDto toDto(Comment comment);

    Comment toComment(CreateCommentDto createCommentDto);

    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateComment(CommentDto commentDto, @MappingTarget Comment comment);

}
