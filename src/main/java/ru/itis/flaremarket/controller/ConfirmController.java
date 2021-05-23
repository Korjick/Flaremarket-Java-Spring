package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.flaremarket.service.ConfirmService;

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/confirm/{code}")
    public String confirmUser(@PathVariable("code") String code) {
        confirmService.confirmUser(code);
        return "redirect:/";
    }
}
