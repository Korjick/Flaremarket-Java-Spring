package ru.itis.flaremarket.service;

import ru.itis.flaremarket.dto.UserDto;
import ru.itis.flaremarket.models.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long userId);
    UserDto getUserByEmailOrNickname(String email, String nickname);
    UserDto getUserByEmail(String email);
    User getRawUserById(Long id);
    User getRawUserByEmailOrNickname(String email, String nickname);
    User getRawUserByEmail(String email);
    UserDto save(User user);
    void delete(Long id);
}