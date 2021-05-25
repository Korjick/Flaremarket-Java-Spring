package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.models.SellItemPrice;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.repository.SellItemPriceRepository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class SellItemPriceServiceImpl implements SellItemPriceService {

    @Autowired
    private SellItemPriceRepository sellItemPriceRepository;

    @Override
    public void addSellItemPrice(Long sellItemId, Double price) {
        sellItemPriceRepository.save(SellItemPrice.builder()
                .sellItemId(sellItemId)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .price(price)
                .build());
    }

    @Override
    public List<Long> findAllSellItemIdOf24H() {
        return sellItemPriceRepository.findAllSellItemIdOf24H();
    }

    @Override
    public List<Long> findAllSellItemIdOf12H() {
        return sellItemPriceRepository.findAllSellItemIdOf12H();
    }

    @Override
    public List<Long> findAllSellItemIdOf8H() {
        return sellItemPriceRepository.findAllSellItemIdOf8H();
    }

    @Override
    public List<Double> getSellItemPrices(Long sellItemId) {
        return sellItemPriceRepository.getAllPriceDesc(sellItemId);
    }
}
