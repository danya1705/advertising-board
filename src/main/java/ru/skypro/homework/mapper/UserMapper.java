package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.image.filePath", target = "image")
    abstract UserDto toDto(User user);

    @Mapping(source = "userDto.image", target = "user.image.filePath")
    abstract void updateUser(UserDto userDto, @MappingTarget User user);

    @Mapping(source = "newPasswordDto.newPassword", target = "password")
    abstract void updateUserPassword(NewPasswordDto newPasswordDto, @MappingTarget User user);
}