package controllers;

import models.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.UserService;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home() {
        return "Users";
    }

    @RequestMapping(value = "/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping("/id/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

}
