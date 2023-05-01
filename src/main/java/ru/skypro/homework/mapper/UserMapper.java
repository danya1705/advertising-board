package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.image.filePath", target = "image")
    UserDto toDto(User user);

    @Mapping(source = "userDto.image", target = "user.image.filePath")
    void updateUser(UserDto userDto, @MappingTarget User user);

    @Mapping(source = "newPasswordDto.newPassword", target = "password")
    void updateUserPassword(NewPasswordDto newPasswordDto, @MappingTarget User user);
}