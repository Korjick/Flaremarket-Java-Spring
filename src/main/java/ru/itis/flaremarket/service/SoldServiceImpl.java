package ru.itis.flaremarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.flaremarket.security.details.UserDetailsImpl;

@Service
public class SoldServiceImpl implements SoldService {

    @Autowired
    private SellService sellService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSoldService userSoldService;

    @Override
    public void soldItem(Long itemId, Long userId) throws Exception {
        // sell config
        userSoldService.addUserSold(userService.getUserByEmail(((UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername()).getId());
        sellService.removeSellItem(itemId);
    }
}
