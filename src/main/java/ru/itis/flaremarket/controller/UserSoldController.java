package ru.itis.flaremarket.controller;

import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.flaremarket.security.details.UserDetailsImpl;
import ru.itis.flaremarket.service.UserService;
import ru.itis.flaremarket.service.UserSoldService;

import java.util.List;
import java.util.Map;

@Controller
public class UserSoldController {

    @Autowired
    private UserSoldService userSoldService;

    @Autowired
    private UserService userService;

    @GetMapping("/solds/lastweek")
    @ResponseBody
    public List<Pair<String, Long>> getLastWeekSolds(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userSoldService.getLastWeekSolds(userService.getUserByEmail(userDetails.getUsername()).getId());
    }
}
