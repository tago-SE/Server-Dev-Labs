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
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setId("1337");
        return u;
    }

    @RequestMapping("/register/{username}/{password}")
    public User registerUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setId("1337");
        return u;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User update(User user) {
        user.setUsername("UPDATED");
        return user;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public User delete(User user) {
        user.setUsername("DELETED");
        return user;
    }

    @RequestMapping("/get/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @RequestMapping("/get/name/{username}")
    public User getUserByName(@PathVariable("username") String name) {
        return userService.getUser(name);
    }

    @RequestMapping(value = "/all")
    public List<User> getAll() {
        return userService.getAll();
    }

}