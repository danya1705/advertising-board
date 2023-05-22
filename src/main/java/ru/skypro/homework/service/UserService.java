package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
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

    public UserDto getUserInfo(String username) {
        return userMapper.toDto(getUserByUsername(username));
    }

    public UserDto updateUser(UserDto userDto, String username) {
        User user = getUserByUsername(username);
        userMapper.updateUser(userDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

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

    public void editUserImage(MultipartFile imageFile, String username) throws IOException {
        User user = getUserByUsername(username);
        user.setImage(imageService.uploadImage(imageFile));
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
