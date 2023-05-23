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

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    /**
     * Создание UserDto по модели User
     *
     * @param user пользователь
     * @return UserDto
     */
    default UserDto toDto(User user) {

        if (user == null) {
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
            userDto.setImage("/image/" + image.getId());
        } else {
            userDto.setImage(null);
        }

        return userDto;
    }

    /**
     * Изменение данных о пользователе
     *
     * @param userDto ID объявления
     * @param user    пользователь
     */
    @Mapping(target = "user.image", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    void updateUser(UserDto userDto, @MappingTarget User user);

    /**
     * Изменение пароля
     *
     * @param newPasswordDto Dto с обновленным паролем
     * @param user           пользователь
     */
    @Mapping(source = "newPasswordDto.newPassword", target = "password")
    void updateUserPassword(NewPasswordDto newPasswordDto, @MappingTarget User user);

    /**
     * Соотнесение данных из Dto в сущность пользователя
     *
     * @param registerReqDto Dto с данными зарегестрированного пользователя
     */
    @Mapping(source = "username", target = "userName")
    @Mapping(source = "username", target = "email")
    User toUser(RegisterReqDto registerReqDto);

}