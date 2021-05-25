package ru.itis.flaremarket.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.models.Bet;
import ru.itis.flaremarket.models.SellItem;
import ru.itis.flaremarket.repository.BetRepository;
import ru.itis.flaremarket.security.details.UserDetailsImpl;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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
    private SellItemPriceService sellItemPriceService;

    @Autowired
    private UserService userService;

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

            Optional<SellItem> sellItem = sellService.findSellItemById(bet.get().getId());
            sellItem.ifPresent(s -> {
                List<Double> prices = sellItemPriceService.getSellItemPrices(s.getId());
                s.setChanee((s.getPrice() + 1) / prices.get(prices.size() - 1));
                s.setPrice(s.getPrice() + 1);
                sellService.save(s);
                sellItemPriceService.addSellItemPrice(s.getId(), s.getPrice());
            });
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
                        soldService.soldItem(sellItemId, userService.getUserByEmail(((UserDetailsImpl) SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal()).getUsername()).getId(), current.getBuyerId());
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
