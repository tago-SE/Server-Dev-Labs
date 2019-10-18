package controllers;

import models.User;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import services.UserService;

import java.util.*;

import static org.junit.Assert.*;


/**
 * This test requires that the service is running and can be reached
 */
public class UserControllerTest {

    // To prevent blocking of the application due to invalid JUnit test configuration of host address
    // should be set to true unless explicitly you want unit tests to be ran from here
    private static final boolean IGNORE_TESTS = true;

    private static final String HOST    = "192.168.99.100";
    private static final int PORT       = 9091;

    private static final String URL = "http://" + HOST + ":" + PORT + "/users";

    private static List<User> createdUsers = new ArrayList<>();
    private static UserService s = new UserService();

    private static UserClient client = new UserClient(HOST, PORT);


    @Test
    public void index() {
        if (IGNORE_TESTS)
            return;
        String result = new RestTemplate().getForObject(URL, String.class);
        assertEquals(result, "Users");
    }

    @Test
    public void loginUser() {
        if (IGNORE_TESTS)
            return;
        String name = "" + Math.random();
        User u = s.register(name, name);
        createdUsers.add(u);

        User loginUser = client.loginUser(name, name);
        assertNotNull(loginUser);
        assertNull(client.loginUser(name, name + "asdasdassd"));
    }

    @Test
    public void registerUser() {
        if (IGNORE_TESTS)
            return;
        String name = "" + Math.random();
        User u = client.registerUser(new User(name, name));
        createdUsers.add(u);
        assertNotNull(u);
    }

    @Test
    public void update() {
        if (IGNORE_TESTS)
            return;
        String name = "" + Math.random();
        User u = client.registerUser(new User(name, name));
        createdUsers.add(u);
        u.setEmail("julian@kth.se");
        client.updateUser(u);
        User u2 = client.getUserById(u.getId());
        assertEquals(u2.getId(), u.getId());
    }

    @Test
    public void delete() {
        if (IGNORE_TESTS)
            return;

        String name = "" + Math.random();
        User u = s.register(name, name);
        client.deleteUser(u);
        assertNull(client.getUserById(u.getId()));
    }

    @Test
    public void getUserById() {
        if (IGNORE_TESTS)
            return;
        String name = "" + Math.random();
        User u = client.registerUser(new User(name, name));
        createdUsers.add(u);
        User u2 = client.getUserById(u.getId());
        assertEquals(u2.getId(), u.getId());
        assertNull(client.getUserById(-5));
    }

    @Test
    public void getUserByName() {
        if (IGNORE_TESTS)
            return;
        String name = "" + Math.random();
        User u = s.register(name, name);
        createdUsers.add(u);
        User found = client.getUserByName(name);
        assertEquals(u.getUsername(), found.getUsername());
    }

    @Test
    public void getAll() {
        if (IGNORE_TESTS)
            return;

        int count = client.getAllUsers().size();
        String name = "" + Math.random();
        User u = client.registerUser(new User(name, name));
        createdUsers.add(u);
        List<User> users = client.getAllUsers();
        assertEquals(count + 1, users.size());
        assertNotNull(users.get(0));
    }

    @AfterClass
    public static void cleanup() {
        for (User u : createdUsers) {
            s.delete(u);
        }
    }
}
