package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.models.enums.user.UserState;
import ru.itis.flaremarket.repository.UserRepository;

import java.util.Optional;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void confirmUser(String code) {
        Optional<User> userCredential = userRepository.findByConfirmCode(code);
        if (userCredential.isPresent()) {
            User user = userCredential.get();
            user.setState(UserState.CONFIRMED);
            userRepository.save(user);
        }
    }
}
