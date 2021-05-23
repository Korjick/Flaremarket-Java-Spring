package ru.itis.flaremarket.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.models.enums.sellitem.Game;
import ru.itis.flaremarket.models.enums.sellitem.SellType;

public interface SellService {
    void createSellItem(MultipartFile file, Game game, SellType sellType, String platform, Double price, String description) throws Exception;
    void removeSellItem(Long sellItemId) throws Exception;
}
