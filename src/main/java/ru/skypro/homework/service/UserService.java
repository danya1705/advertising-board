package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapperImpl;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final UserMapperImpl userMapper;
    private final PasswordEncoder encoder;

    /**
     * Получение информации о пользователе в виде DTO
     *
     * @param username Username пользователя
     */
    public UserDto getUserInfo(String username) {
        return userMapper.toDto(getUserByUsername(username));
    }

    /**
     * Изменение данных о пользователе
     *
     * @param userDto  DTO с новыми данными о пользователе, которые будут
     *                 записаны взамен старых данных
     * @param username Username пользователя
     * @return UserDto
     */
    public UserDto updateUser(UserDto userDto, String username) {
        User user = getUserByUsername(username);
        userMapper.updateUser(userDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    /**
     * Изменение пароля пользователя
     *
     * @param newPasswordDto DTO с новым паролем пользователя
     * @param username       Username пользователя
     * @return boolean
     */
    public boolean editUserPassword(NewPasswordDto newPasswordDto, String username) {
        User user = getUserByUsername(username);
        if (encoder.matches(newPasswordDto.getCurrentPassword(), user.getPassword())) {
            newPasswordDto.setNewPassword(encoder.encode(newPasswordDto.getNewPassword()));
            userMapper.updateUserPassword(newPasswordDto, user);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Изменение изображения пользователя
     *
     * @param imageFile файл изображения
     * @param username  username пользователя
     */
    public void editUserImage(MultipartFile imageFile, String username) throws IOException {
        User user = getUserByUsername(username);
        Image oldImage = user.getImage();
        user.setImage(imageService.uploadImage(imageFile));
        userRepository.save(user);
        imageService.deleteImage(oldImage);
    }

    /**
     * Получение пользователя из БД по username
     *
     * @param username Username пользователя
     * @return User
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
