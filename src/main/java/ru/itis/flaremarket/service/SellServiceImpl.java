package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.models.SellItem;
import ru.itis.flaremarket.models.enums.sellitem.Game;
import ru.itis.flaremarket.models.enums.sellitem.SellType;
import ru.itis.flaremarket.repository.SellItemRepository;
import ru.itis.flaremarket.security.details.UserDetailsImpl;

import java.util.*;

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
                .chanee(1.0d)
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

    @Override
    public List<SellItem> getAllSellItemsBySellType(SellType sellType) {
        return sellItemRepository.findAllBySellType(sellType);
    }

    @Override
    public List<SellItem> findAllBySellTypeOf24H(SellType sellType) {
        Set<Long> ids = new HashSet<>(sellItemPriceService.findAllSellItemIdOf24H());
        List<SellItem> sellItems = new ArrayList<>();
        for(Long id : ids) {
            sellItemRepository.findById(id).ifPresent(c -> {
                if(c.getSellType().equals(sellType))
                    sellItems.add(c);
            });
        }

        return sellItems;
    }

    @Override
    public List<SellItem> findAllBySellTypeOf12H(SellType sellType) {
        Set<Long> ids = new HashSet<>(sellItemPriceService.findAllSellItemIdOf12H());
        List<SellItem> sellItems = new ArrayList<>();
        for(Long id : ids) {
            sellItemRepository.findById(id).ifPresent(c -> {
                if(c.getSellType().equals(sellType))
                    sellItems.add(c);
            });
        }

        return sellItems;
    }

    @Override
    public List<SellItem> findAllBySellTypeOf8H(SellType sellType) {
        Set<Long> ids = new HashSet<>(sellItemPriceService.findAllSellItemIdOf8H());
        List<SellItem> sellItems = new ArrayList<>();
        for(Long id : ids) {
            sellItemRepository.findById(id).ifPresent(c -> {
                if(c.getSellType().equals(sellType))
                    sellItems.add(c);
            });
        }

        return sellItems;
    }

    @Override
    public Long findUserIdByItemId(Long itemId) {
        Optional<SellItem> item = sellItemRepository.findById(itemId);
        return item.map(SellItem::getUserId).orElse(null);
    }

    @Override
    public List<SellItem> findAllByOrderByChaneeAsc() {
        return sellItemRepository.findAllByOrderByChaneeAsc();
    }

    @Override
    public Optional<SellItem> findSellItemById(Long sellItemId) {
        return sellItemRepository.findById(sellItemId);
    }

    @Override
    public void save(SellItem sellItem) {
        sellItemRepository.save(sellItem);
    }

    @Override
    public void updateSellItem(Long sellItemId, Double price) {
        Optional<SellItem> sellItem = sellItemRepository.findById(sellItemId);
        if(sellItem.isPresent()) {
            List<Double> prices = sellItemPriceService.getSellItemPrices(sellItemId);

            sellItem.get().setPrice(price);
            sellItem.get().setChanee(price / prices.get(prices.size() - 1));
            sellItemPriceService.addSellItemPrice(sellItemId, price);
            sellItemRepository.save(sellItem.get());
        }
    }
}
