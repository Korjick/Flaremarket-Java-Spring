package ru.itis.flaremarket.service;

import ru.itis.flaremarket.models.Bet;

import java.sql.Date;
import java.util.Optional;

public interface BetService {
    Bet createBet(Long sellItemId) throws Exception;
    void updateBet(Long sellItemId, Long buyerId);
    void executeBet(Long sellItemId) throws Exception;
}
