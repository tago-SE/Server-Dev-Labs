package controllers;

import models.Dummy;
import models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import javax.validation.Valid;
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

    //@RequestMapping("/login/{username}/{password}")
    //public User loginUser(@PathVariable("username") String username, @PathVariable("password") String password) {
    @PostMapping("/login")
    public User loginUser(@Valid @RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    //@RequestMapping("/register/{username}/{password}")
    // public User registerUser(@PathVariable("username") String username, @PathVariable("password") String password) {

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword());
    }

    @Deprecated
    @PutMapping("/updateDummy/{id}")
    public ResponseEntity<Dummy> updateDummy(@PathVariable(value = "id") Long id,  @Valid @RequestBody Dummy dummy) {
        System.out.println("RECV: " + dummy);
        dummy.setId(55);
        dummy.setName("UPDATE_" + dummy.getName());
        return ResponseEntity.ok(dummy);
    }

   @PutMapping("/update/{id}")
   public ResponseEntity<User> updateUser(
           @PathVariable(value = "id") Long id,
           @Valid @RequestBody User user) {
       return ResponseEntity.ok(userService.update(user));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.delete(user));
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
