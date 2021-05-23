package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.flaremarket.dto.UserDto;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.repository.UserRepository;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(userRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long userId) {
        return UserDto.from(userRepository.findById(userId).orElse(User.builder().build()));
    }

    @Override
    public UserDto getUserByEmailOrNickname(String email, String nickname) {
        return UserDto.from(userRepository.findByEmailOrNickname(email, nickname).orElse(User.builder().build()));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserDto.from(userRepository.findByEmail(email).orElse(User.builder().build()));
    }

    @Override
    public User getRawUserByEmailOrNickname(String email, String nickname) {
        return userRepository.findByEmailOrNickname(email, nickname).orElse(User.builder().build());
    }

    @Override
    public User getRawUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(User.builder().build());
    }

    @Override
    public User getRawUserById(Long id) {
        return userRepository.findById(id).orElse(User.builder().build());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
