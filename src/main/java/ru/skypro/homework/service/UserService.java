package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.logging.Logger;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger("UserServiceLogger");

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseGet(() -> {
                            logger.info("User with id=" + id + " noy found");
                            return null;
                        }
                );
    }
}
