package ru.skypro.homework.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    public static final int USER_ID = 1;
    public static final int WRONG_USER_ID = 2;
    public static final String USER_NAME = "CorrectFullName";
    public static final String WRONG_USER_NAME = "WrongFullName";
    public static final String USER_PASSWORD = "1234";
    public static final String WRONG_USER_PASSWORD = "1111";
    public static final String FIRST_NAME = "FirstName";
    public static final String WRONG_FIRST_NAME = "WrongFirstName";
    public static final String LAST_NAME = "LastName";
    public static final String WRONG_LAST_NAME = "WrongLastName";
    public static final String PHONE = "11111111111";
    public static final String WRONG_PHONE = "22222222222";
    public static final String EMAIL = "Name@mail.ru";
    public static final String WRONG_EMAIL = "WrongName@mail.ru";
    public static final String PATH_TO_IMAGE = "filePath";
    public static final String WRONG_PATH_TO_IMAGE = "wrongFilePath";


    @InjectMocks
    private UserMapperImpl userMapper;

    @Test
    void toDtoTest() {

        Image image = new Image();
        image.setFilePath(PATH_TO_IMAGE);

        User user = new User();
        user.setId(USER_ID);
        user.setUserName(USER_NAME);
        user.setPassword(WRONG_USER_PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setPhone(PHONE);
        user.setEmail(EMAIL);
        user.setImage(image);

        UserDto dto = userMapper.toDto(user);

        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getId()).isEqualTo(USER_ID);
        Assertions.assertThat(dto.getFirstName()).isEqualTo(FIRST_NAME);
        Assertions.assertThat(dto.getLastName()).isEqualTo(LAST_NAME);
        Assertions.assertThat(dto.getPhone()).isEqualTo(PHONE);
        Assertions.assertThat(dto.getEmail()).isEqualTo(EMAIL);
        Assertions.assertThat(dto.getImage()).isEqualTo(PATH_TO_IMAGE);
    }

    @Test
    void updateUserTest() {
        Image wrongImage = new Image();
        wrongImage.setFilePath(WRONG_PATH_TO_IMAGE);
        Image image = new Image();
        image.setFilePath(PATH_TO_IMAGE);

        User user = new User();
        user.setId(WRONG_USER_ID);
        user.setUserName(WRONG_USER_NAME);
        user.setPassword(WRONG_USER_PASSWORD);
        user.setFirstName(WRONG_FIRST_NAME);
        user.setLastName(WRONG_LAST_NAME);
        user.setPhone(WRONG_PHONE);
        user.setEmail(WRONG_EMAIL);
        user.setImage(wrongImage);

        UserDto userDto = new UserDto();
        userDto.setId(USER_ID);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setPhone(PHONE);
        userDto.setEmail(EMAIL);
        userDto.setImage(image.getFilePath());

        userMapper.updateUser(userDto, user);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isEqualTo(USER_ID);
        Assertions.assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
        Assertions.assertThat(user.getLastName()).isEqualTo(LAST_NAME);
        Assertions.assertThat(user.getPhone()).isEqualTo(PHONE);
        Assertions.assertThat(user.getEmail()).isEqualTo(EMAIL);
        Assertions.assertThat(user.getImage().getFilePath()).isEqualTo(PATH_TO_IMAGE);
    }

    @Test
    void updateUserPasswordTest() {

        User user = new User();
        user.setId(USER_ID);
        user.setUserName(USER_NAME);
        user.setPassword(WRONG_USER_PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setPhone(PHONE);
        user.setEmail(EMAIL);

        NewPasswordDto newPasswordDto = new NewPasswordDto();
        newPasswordDto.setCurrentPassword(WRONG_USER_PASSWORD);
        newPasswordDto.setNewPassword(USER_PASSWORD);

        userMapper.updateUserPassword(newPasswordDto, user);

        Assertions.assertThat(user.getPassword()).isNotNull();
        Assertions.assertThat(user.getPassword()).isEqualTo(USER_PASSWORD);

    }
}
