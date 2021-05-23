package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.flaremarket.security.details.UserDetailsImpl;
import ru.itis.flaremarket.service.UserService;

@Controller
public class RecommendationController {

    @Autowired
    private UserService userService;

    @GetMapping("/recommendation")
    public String recommendation(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        model.addAttribute("user", userService.getUserByEmail(userDetails.getUsername()));
        return "recommendation";
    }
}
