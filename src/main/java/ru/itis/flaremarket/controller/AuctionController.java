package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.flaremarket.models.enums.sellitem.SellType;
import ru.itis.flaremarket.security.details.UserDetailsImpl;
import ru.itis.flaremarket.service.BetService;
import ru.itis.flaremarket.service.SellService;
import ru.itis.flaremarket.service.UserService;

@Controller
public class AuctionController {

    @Autowired
    private UserService userService;

    @Autowired
    private SellService sellService;

    @Autowired
    private BetService betService;

    @GetMapping("/auction")
    public String auction(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        model.addAttribute("user", userService.getUserByEmail(userDetails.getUsername()));
        model.addAttribute("bets", sellService.getAllSellItemsBySellType(SellType.AUCTION));
        return "auction";
    }

    @PostMapping("/auction/bet")
    public String bet(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long sellItemId){
        betService.updateBet(sellItemId, userService.getUserByEmail(userDetails.getUsername()).getId());
        return "redirect:/auction";
    }
}
