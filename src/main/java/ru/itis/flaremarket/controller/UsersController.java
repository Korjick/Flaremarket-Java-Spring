package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.flaremarket.dto.UserDto;
import ru.itis.flaremarket.models.User;
import ru.itis.flaremarket.service.UserService;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserService usersService;

    @RequestMapping(value = "/users",
            method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> getEmployees() {
        return usersService.getAllUsers();
    }

    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public UserDto getEmployee(@PathVariable("id") Long id) {
        return usersService.getUserById(id);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST)
    @ResponseBody
    public UserDto addEmployee(@RequestBody User user) {
        return usersService.save(user);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.PUT)
    @ResponseBody
    public UserDto updateEmployee(@RequestBody User usr) {
        return usersService.save(usr);
    }

    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmployee(@PathVariable("id") Long id) {
        usersService.delete(id);
    }
}
