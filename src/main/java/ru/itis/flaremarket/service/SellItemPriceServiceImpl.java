package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.models.SellItemPrice;
import ru.itis.flaremarket.repository.SellItemPriceRepository;

import java.sql.Date;
import java.sql.Time;

@Service
public class SellItemPriceServiceImpl implements SellItemPriceService {

    @Autowired
    private SellItemPriceRepository sellItemPriceRepository;

    @Override
    public void addSellItemPrice(Long sellItemId, Double price) {
        sellItemPriceRepository.save(SellItemPrice.builder()
                .sellItemId(sellItemId)
                .date(new Date(System.currentTimeMillis()))
                .time(new Time(System.currentTimeMillis()))
                .price(price)
                .build());
    }
}
