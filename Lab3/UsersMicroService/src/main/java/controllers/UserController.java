package controllers;

import models.User;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("")
    public String index() {
        return "Users";
    }

    @RequestMapping("/login/{username}/{password}")
    public User loginUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        return userService.login(username, password);
    }

    @RequestMapping("/register/{username}/{password}")
    public User registerUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        return userService.register(username, password);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean update(User user) {
       return userService.update(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public User delete(User user) {
        return userService.delete(user);
    }

    @RequestMapping("/get/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/get/name/{username}")
    public User getUserByName(@PathVariable("username") String name) {
        return userService.getUserByName(name);
    }

    @RequestMapping(value = "/all")
    public List<User> getAll() {
        return userService.getAll();
    }
}
