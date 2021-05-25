package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.flaremarket.models.SellItem;
import ru.itis.flaremarket.models.enums.sellitem.SellType;

import java.util.List;
import java.util.Optional;

public interface SellItemRepository extends JpaRepository<SellItem, Long> {
    List<SellItem> findAllBySellType(SellType sellType);
    List<SellItem> findAllByOrderByChaneeAsc();
}
