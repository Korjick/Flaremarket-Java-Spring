package ru.itis.flaremarket.controller;

import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.convertor.StringToGameConverter;
import ru.itis.flaremarket.convertor.StringToSellTypeConverter;
import ru.itis.flaremarket.models.SellItem;
import ru.itis.flaremarket.models.enums.sellitem.Game;
import ru.itis.flaremarket.models.enums.sellitem.SellType;
import ru.itis.flaremarket.security.details.UserDetailsImpl;
import ru.itis.flaremarket.service.FileStorageService;
import ru.itis.flaremarket.service.SellService;
import ru.itis.flaremarket.service.UserService;

import java.util.List;

@Controller
public class SellController {

    @Autowired
    private SellService sellService;

    @Autowired
    private UserService userService;

    @PostMapping("/sell")
    public String createSellItem(@RequestParam MultipartFile file, @RequestParam Game game,
                               @RequestParam SellType sellType, @RequestParam String platform,
                               @RequestParam Double price, @RequestParam String description) throws Exception {
        sellService.createSellItem(file, game, sellType, platform, price, description);
        return "redirect:/recommendation";
    }

    @PostMapping("/sell/update")
    public String updateSellItem(@RequestParam Double price, @RequestParam Long sellItemId){
        sellService.updateSellItem(sellItemId, price);
        return "redirect:/myitems";
    }

    @GetMapping("/myitems")
    public String myItems(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        model.addAttribute("user", userService.getUserByEmail(userDetails.getUsername()));
        model.addAttribute("bets", sellService.getAllSellItemsBySellType(SellType.AUCTION));
        model.addAttribute("primary", sellService.getAllSellItemsBySellType(SellType.PRIMARY));
        return "myitems";
    }
}
