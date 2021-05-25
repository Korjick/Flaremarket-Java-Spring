package ru.itis.flaremarket.service;

import ru.itis.flaremarket.models.User;

import java.util.List;
import java.util.Optional;

public interface SellItemPriceService {
    void addSellItemPrice(Long sellItemId, Double price);
    List<Long> findAllSellItemIdOf24H();
    List<Long> findAllSellItemIdOf12H();
    List<Long> findAllSellItemIdOf8H();
    List<Double> getSellItemPrices(Long sellItemId);
}
