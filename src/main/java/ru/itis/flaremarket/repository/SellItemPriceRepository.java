package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flaremarket.models.SellItem;
import ru.itis.flaremarket.models.SellItemPrice;

public interface SellItemPriceRepository extends JpaRepository<SellItemPrice, Long> {
}
