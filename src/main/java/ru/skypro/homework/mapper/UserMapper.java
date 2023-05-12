package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

import java.nio.file.Path;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.email", target = "email")
    @Mapping(target = "image",
            expression = "java(\"/img/\" + java.nio.file.Path.of(user.getImage().getFilePath()).getFileName().toString())")
    default UserDto toDto(User user) {

        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());

        Image image = user.getImage();
        if (image != null) {
            userDto.setImage("/img/" + Path.of(image.getFilePath()).getFileName().toString());
        } else {
            userDto.setImage(null);
        }

        return userDto;
    }

    @Mapping(target = "user.image", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateUser(UserDto userDto, @MappingTarget User user);

    @Mapping(source = "newPasswordDto.newPassword", target = "password")
    void updateUserPassword(NewPasswordDto newPasswordDto, @MappingTarget User user);

    @Mapping(source = "username", target = "userName")
    @Mapping(source = "username", target = "email")
    User toUser(RegisterReqDto registerReqDto);

}