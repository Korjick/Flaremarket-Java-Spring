package ru.itis.flaremarket.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {
    void setImage(Long userId, MultipartFile image);
}
