package ru.itis.flaremarket.service;

import javafx.util.Pair;
import ru.itis.flaremarket.dto.UserSoldsDto;

import java.util.List;

public interface UserSoldService {
    void addUserSold(Long userId, Long buyerId);
    List<UserSoldsDto> getAllSolds(Long userId);
    List<Pair<String, Long>> getLastWeekSolds(Long userId);
}
