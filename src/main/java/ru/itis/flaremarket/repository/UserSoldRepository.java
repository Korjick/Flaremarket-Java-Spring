package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.flaremarket.models.UserSold;

import java.util.List;

public interface UserSoldRepository extends JpaRepository<UserSold, Long> {
    List<UserSold> findAllByUserId(Long userId);
    @Query(value = "SELECT * from users_sold WHERE date BETWEEN NOW()\\:\\:DATE-EXTRACT(DOW FROM NOW())\\:\\:INTEGER-7 AND NOW()\\:\\:DATE-EXTRACT(DOW from NOW())\\:\\:INTEGER and user_id=:userId", nativeQuery = true)
    List<UserSold> getLastWeekSolds(@Param("userId") Long userId);
}
