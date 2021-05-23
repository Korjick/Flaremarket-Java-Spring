package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flaremarket.models.UserSold;

public interface UserSoldRepository extends JpaRepository<UserSold, Long> {
}
