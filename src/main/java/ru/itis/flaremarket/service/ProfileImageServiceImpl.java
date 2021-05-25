package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.models.ProfileImage;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.repository.ProfileImageRepository;

import java.util.Optional;

@Service
public class ProfileImageServiceImpl implements ProfileImageService {

    @Autowired
    private ProfileImageRepository profileImageRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;

    @Override
    public void setImage(Long userId, MultipartFile image) {
        User user = userService.getRawUserById(userId);
        user.setProfileImage(profileImageRepository.save(ProfileImage.builder().id(userId).profileImageUrl(fileStorageService.saveFile(image)).build()));
        userService.save(user);
    }
}
