package ru.itis.flaremarket.service;

import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.convertor.StringToGameConverter;
import ru.itis.flaremarket.convertor.StringToSellTypeConverter;
import ru.itis.flaremarket.models.Bet;
import ru.itis.flaremarket.models.SellItem;
import ru.itis.flaremarket.models.enums.sellitem.Game;
import ru.itis.flaremarket.models.enums.sellitem.SellType;
import ru.itis.flaremarket.repository.BetRepository;
import ru.itis.flaremarket.repository.SellItemRepository;
import ru.itis.flaremarket.security.details.UserDetailsImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;

@Service
public class SellServiceImpl implements SellService {

    @Autowired
    private SellItemRepository sellItemRepository;

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SellItemPriceService sellItemPriceService;

    @Override
    public void createSellItem(MultipartFile file, Game game, SellType sellType, String platform, Double price, String description) throws Exception {
        SellItem sellItem = sellItemRepository.save(SellItem
                .builder()
                .image(fileStorageService.saveFile(file))
                .game(game)
                .sellType(sellType)
                .platform(platform)
                .price(price)
                .description(description)
                .userId(userService.getUserByEmail(((UserDetailsImpl) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()).getUsername()).getId())
                .build());
        if(sellType.equals(SellType.AUCTION)) {
            sellItem.setBet(betService.createBet(sellItem.getId()));
            sellItemRepository.save(sellItem);
        }

        sellItemPriceService.addSellItemPrice(sellItem.getId(), sellItem.getPrice());
    }

    @Override
    public void removeSellItem(Long sellItemId) throws Exception {
        Optional<SellItem> sellItem = sellItemRepository.findById(sellItemId);
        if(sellItem.isPresent()) {
            sellItemRepository.delete(sellItem.get());
        } else {
            throw new Exception("Игровой предмет не найден");
        }
    }
}
