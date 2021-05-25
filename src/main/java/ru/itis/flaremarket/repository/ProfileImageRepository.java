package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flaremarket.models.ProfileImage;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}
