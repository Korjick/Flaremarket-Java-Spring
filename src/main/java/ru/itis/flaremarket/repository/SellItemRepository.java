package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flaremarket.models.SellItem;

import java.util.Optional;

public interface SellItemRepository extends JpaRepository<SellItem, Long> {
    Optional<SellItem> findById(Long id);
}
