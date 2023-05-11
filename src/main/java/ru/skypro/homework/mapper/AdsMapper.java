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
    @Mapping(target = "image",
            expression = "java(\"/img/\" + java.nio.file.Path.of(ad.getImage().getFilePath()).getFileName().toString())")
    AdsDto toDto(Ads ad);

    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(target = "image",
            expression = "java(\"/img/\" + java.nio.file.Path.of(ad.getImage().getFilePath()).getFileName().toString())")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "author.email", target = "email")
    FullAdsDto toFullAdsDto(Ads ad);

    Ads toAds(CreateAdsDto createAdsDto);

    void updateAds(CreateAdsDto createAdsDto, @MappingTarget Ads ad);
}
