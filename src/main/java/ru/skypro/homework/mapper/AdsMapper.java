package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;



@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, imports = User.class)
public interface AdsMapper {


    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "image.filePath", target = "image")
    AdsDto toDto(Ads ad);

    CreateAdsDto newToAds(Ads ad);

    @Mapping(source = "image", target = "image.filePath")
    Ads toNFullAdsDto(FullAdsDto fullAdsDto);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "image", target = "image.filePath")
    void updateModel(AdsDto adsDto,@MappingTarget Ads ad);



}
