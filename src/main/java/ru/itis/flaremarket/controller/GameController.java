package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.flaremarket.service.GameService;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games/platforms")
    @ResponseBody
    public List<String> getAllGames(){
        return gameService.getAllPlatforms();
    }

}
