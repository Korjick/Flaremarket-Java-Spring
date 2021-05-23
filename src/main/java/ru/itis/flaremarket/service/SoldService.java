package ru.itis.flaremarket.service;

public interface SoldService {
    void soldItem(Long itemId, Long userId) throws Exception;
}
