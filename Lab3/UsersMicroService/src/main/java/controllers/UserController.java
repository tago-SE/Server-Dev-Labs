package controllers;

import models.User;
import models.UserList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/login")
    public User loginUser(@Valid @RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword());
    }

   @PutMapping("/update")
   public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
       return ResponseEntity.ok(userService.update(user));
    }

    @PostMapping("/delete")
    public Map<String, Boolean> deleteUser(@Valid @RequestBody User user) {
        boolean result = userService.delete(user) != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", result);
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/get/name/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable("username") String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<UserList> getAll() {
        return ResponseEntity.ok(new UserList(userService.getAll()));
    }
}
