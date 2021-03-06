package services;

import models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserService() {

    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        User u = new User();
        u.setUsername("Tiago");
        users.add(u);
        u = new User();
        u.setUsername("Alex");
        users.add(u);
        return users;
    }

    public User getUser(String id) {
        User u = new User();
        u.setId(id);
        u.setUsername("Tiago");
        u.setPassword("123123");
        return u;
    }

}
