package ru.itis.flaremarket.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.flaremarket.security.details.UserDetailsImpl;
import ru.itis.flaremarket.service.UserServiceImpl;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) return "redirect:/recommendation";
        return "index";
    }

    @GetMapping("/signIn")
    public String signIn(){
        return "redirect:/";
    }
}
