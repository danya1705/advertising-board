package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.User;
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

    public void editUserImage(int userId, MultipartFile imageFile) throws IOException {
        User user = getUserById(userId);
        user.setImage(imageService.uploadImage(imageFile));
        userRepository.save(user);
    }
}
