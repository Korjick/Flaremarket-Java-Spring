package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.flaremarket.models.SellItem;
import ru.itis.flaremarket.models.SellItemPrice;
import ru.itis.flaremarket.models.enums.sellitem.SellType;

import java.util.List;

public interface SellItemPriceRepository extends JpaRepository<SellItemPrice, Long> {
    @Query(value = "select sell_item_id from sell_items_price WHERE NOW() > timestamp\\:\\:timestamptz AND NOW() - timestamp\\:\\:timestamptz <= interval '24 hours' ORDER BY timestamp DESC;", nativeQuery = true)
    List<Long> findAllSellItemIdOf24H();
    @Query(value = "select sell_item_id from sell_items_price WHERE NOW() > timestamp\\:\\:timestamptz AND NOW() - timestamp\\:\\:timestamptz <= interval '12 hours' ORDER BY timestamp DESC;", nativeQuery = true)
    List<Long> findAllSellItemIdOf12H();
    @Query(value = "select sell_item_id from sell_items_price WHERE NOW() > timestamp\\:\\:timestamptz AND NOW() - timestamp\\:\\:timestamptz <= interval '8 hours' ORDER BY timestamp DESC;", nativeQuery = true)
    List<Long> findAllSellItemIdOf8H();
    @Query(value = "select price from sell_items_price where sell_item_id=:sellItemId order by price desc", nativeQuery = true)
    List<Double> getAllPriceDesc(@Param("sellItemId") Long sellItemId);
}
