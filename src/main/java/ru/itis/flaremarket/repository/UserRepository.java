package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flaremarket.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByConfirmCode(String confirmCode);
    Optional<User> findByEmailOrNickname(String email, String nickname);
    Optional<User> findByEmail(String email);
}
