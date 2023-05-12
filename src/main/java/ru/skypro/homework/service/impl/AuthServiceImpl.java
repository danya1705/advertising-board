package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    //  private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


//  @Override
//  public boolean login(String userName, String password) {
//    if (!manager.userExists(userName)) {
//      return false;
//    }
//    UserDetails userDetails = manager.loadUserByUsername(userName);
//    return encoder.matches(password, userDetails.getPassword());
//  }

    @Override
    public boolean login(String userName, String password) {
        return userRepository.findByUserName(userName)
                .map(user -> encoder.matches(password, user.getPassword()))
                .orElse(false);
    }

//    @Override
//    public boolean register(RegisterReqDto registerReqDto, Role role) {
//        if (manager.userExists(registerReqDto.getUsername())) {
//            return false;
//        }
//        manager.createUser(
//                User.builder()
//                        .passwordEncoder(this.encoder::encode)
//                        .password(registerReqDto.getPassword())
//                        .username(registerReqDto.getUsername())
//                        .roles(role.name())
//                        .build());
//        return true;
//    }

    @Override
    public boolean register(RegisterReqDto registerReqDto, Role role) {

        if (userRepository.findByUserName(registerReqDto.getUsername()).isPresent()) {
            return false;
        }

        registerReqDto.setRole(role);
        registerReqDto.setPassword(encoder.encode(registerReqDto.getPassword()));
        userRepository.save(userMapper.toUser(registerReqDto));
        return true;
    }
}
