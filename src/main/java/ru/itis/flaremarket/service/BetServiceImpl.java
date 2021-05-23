package ru.itis.flaremarket.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.models.Bet;
import ru.itis.flaremarket.repository.BetRepository;
import ru.itis.flaremarket.security.details.UserDetailsImpl;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.Executor;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private SoldService soldService;

    @Autowired
    private SellService sellService;

    @Autowired
    private Executor asyncTask;

    @Value("${auction.time}")
    private Long auctionTime;

    @Override
    public Bet createBet(Long sellItemId) {
        Bet bet = betRepository.save(Bet.builder()
                .id(sellItemId).build());
        executeBet(bet.getId());
        return bet;
    }

    @Override
    public void updateBet(Long sellItemId, Long buyerId) {
        Optional<Bet> bet = betRepository.findById(sellItemId);
        if(bet.isPresent()) {
            Bet current = bet.get();
            current.setBuyerId(buyerId);
            betRepository.save(current);
        } else {
            throw new EntityNotFoundException("Bet not found");
        }
    }

    @Async
    @Override
    public void executeBet(Long sellItemId) {
        asyncTask.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(auctionTime);
                Optional<Bet> bet = betRepository.findById(sellItemId);
                if(bet.isPresent()) {
                    Bet current = bet.get();
                    if(current.getBuyerId() != null) {
                        soldService.soldItem(sellItemId, current.getBuyerId());
                    } else {
                        sellService.removeSellItem(sellItemId);
                    }
                } else {
                    throw new EntityNotFoundException("Bet not found");
                }
            }
        });
    }
}
