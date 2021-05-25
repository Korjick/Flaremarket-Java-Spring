package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.flaremarket.security.details.UserDetailsImpl;
import ru.itis.flaremarket.service.SellService;
import ru.itis.flaremarket.service.SoldService;
import ru.itis.flaremarket.service.UserService;

@Controller
public class SoldController {

    @Autowired
    private SoldService soldService;

    @Autowired
    private UserService userService;

    @Autowired
    private SellService sellService;

    @PostMapping("/sold/buy")
    public String buyItem(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long itemId) throws Exception {
        soldService.soldItem(itemId, sellService.findUserIdByItemId(itemId), userService.getUserByEmail(userDetails.getUsername()).getId());
        return "redirect:/recommendation";
    }
}
