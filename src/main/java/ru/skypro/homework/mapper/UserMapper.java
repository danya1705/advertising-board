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
    @Mapping(target = "image",
            expression = "java(\"/img/\" + java.nio.file.Path.of(user.getImage().getFilePath()).getFileName().toString())")
    UserDto toDto(User user);

    @Mapping(target = "user.image", ignore = true)
    void updateUser(UserDto userDto, @MappingTarget User user);

    @Mapping(source = "newPasswordDto.newPassword", target = "password")
    void updateUserPassword(NewPasswordDto newPasswordDto, @MappingTarget User user);
}