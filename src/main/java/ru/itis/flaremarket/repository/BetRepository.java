package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.flaremarket.models.Bet;

import java.util.Optional;

public interface BetRepository extends JpaRepository<Bet, Long> {
    Optional<Bet> findById(Long id);
}
