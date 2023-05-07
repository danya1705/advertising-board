package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapperImpl;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final Logger logger = Logger.getLogger("UserServiceLogger");

    public UserService(UserRepository userRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseGet(() -> {
                            logger.info("User with id=" + id + " noy found");
                            return null;
                        }
                );
    }

    public UserDto getUserInfo(int userId) {
        User user = getUserById(userId);
        UserMapperImpl userMapper = new UserMapperImpl();
        return userMapper.toDto(user);
    }

    public UserDto updateUser(UserDto userDto) {
        UserMapperImpl userMapper = new UserMapperImpl();
        User user = getUserById(userDto.getId());
        userMapper.updateUser(userDto, user);
        userRepository.save(user);
        return userDto;
    }

    public User editUserPassword(int userId, NewPasswordDto newPasswordDto) {
        User user = getUserById(userId);
        UserMapperImpl userMapper = new UserMapperImpl();
        if (!user.getPassword().equals(newPasswordDto.getCurrentPassword())) {
            userMapper.updateUserPassword(newPasswordDto, user);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void editUserImage(int userId, MultipartFile imageFile) throws IOException {
        User user = getUserById(userId);
        user.setImage(imageService.uploadImage(imageFile));
        userRepository.save(user);
    }
}
