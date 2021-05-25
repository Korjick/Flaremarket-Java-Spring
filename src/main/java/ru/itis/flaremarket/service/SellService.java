package ru.itis.flaremarket.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.models.SellItem;
import ru.itis.flaremarket.models.enums.sellitem.Game;
import ru.itis.flaremarket.models.enums.sellitem.SellType;

import java.util.List;
import java.util.Optional;

public interface SellService {
    void createSellItem(MultipartFile file, Game game, SellType sellType, String platform, Double price, String description) throws Exception;
    void removeSellItem(Long sellItemId) throws Exception;
    List<SellItem> getAllSellItemsBySellType(SellType sellType);
    List<SellItem> findAllBySellTypeOf24H(SellType sellType);
    List<SellItem> findAllBySellTypeOf12H(SellType sellType);
    List<SellItem> findAllBySellTypeOf8H(SellType sellType);
    Long findUserIdByItemId(Long itemId);
    List<SellItem> findAllByOrderByChaneeAsc();
    Optional<SellItem> findSellItemById(Long sellItemId);
    void save(SellItem sellItem);
    void updateSellItem(Long sellItemId, Double price);
}
