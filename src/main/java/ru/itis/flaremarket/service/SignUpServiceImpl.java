package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.flaremarket.dto.UserDto;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.models.forms.user.UserForm;
import ru.itis.flaremarket.models.enums.user.UserRole;
import ru.itis.flaremarket.models.enums.user.UserState;

import javax.persistence.EntityExistsException;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailsService mailsService;

    @Override
    public void signUp(UserForm form) {

        UserDto exist = userService.getUserByEmail(form.getEmail());
        if(exist.getEmail() != null) throw new EntityExistsException("User already exist");

        User newUser = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .nickname(form.getNickname())
                .role(UserRole.USER)
                .state(UserState.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        userService.save(newUser);

        mailsService.sendEmailForConfirm(newUser.getEmail(), newUser.getConfirmCode());
    }
}
