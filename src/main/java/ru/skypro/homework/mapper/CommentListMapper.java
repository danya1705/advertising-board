package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.entity.Ads;

@Mapper(componentModel = "spring",
        uses = {CommentMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = Ads.class)
public abstract class CommentListMapper {

    @Mapping(expression = "java(ad.getCommentsList().size())", target = "count")
    @Mapping(source = "commentsList", target = "results")
    abstract ResponseWrapperCommentDto toDto(Ads ad);
}
