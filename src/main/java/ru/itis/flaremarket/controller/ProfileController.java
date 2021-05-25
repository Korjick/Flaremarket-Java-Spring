package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.models.forms.user.ProfileEditForm;
import ru.itis.flaremarket.security.details.UserDetailsImpl;
import ru.itis.flaremarket.service.ProfileImageService;
import ru.itis.flaremarket.service.UserService;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfileImageService profileImageService;

    @PostMapping("/profile/image")
    public String editProfileImage(MultipartFile image, @AuthenticationPrincipal UserDetailsImpl userDetails){
        profileImageService.setImage(userService.getUserByEmail(userDetails.getUsername()).getId(), image);
        return "redirect:/recommendation";
    }

    @PostMapping(value = "/profile/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editProfile(ProfileEditForm form) throws Exception {
        System.out.println(form);
        if(!form.getCode().equals(form.getConfirmCode())) throw new Exception("Неверный код");

        User user = userService.getRawUserByEmail(form.getEmail());

        userService.save(User.builder()
                .nickname(form.getNickname())
                .sellItems(user.getSellItems())
                .solds(user.getSolds())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .id(user.getId())
                .files(user.getFiles())
                .role(user.getRole())
                .confirmCode(user.getConfirmCode())
                .state(user.getState())
                .build());
        return "redirect:/";
    }
}
