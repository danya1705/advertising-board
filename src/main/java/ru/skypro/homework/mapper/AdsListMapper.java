package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AdsMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = User.class)
public interface AdsListMapper {
    @Mapping(expression = "java(user.getAdsList().size())", target = "count")
    @Mapping(source = "adsList", target = "results")
    ResponseWrapperAdsDto toDto(User user);
}
