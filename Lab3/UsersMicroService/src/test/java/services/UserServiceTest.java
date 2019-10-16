package services;

import models.User;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class UserServiceTest {

    private static List<User> createdUsers = new ArrayList<>();
    private static UserService s = new UserService();

    @Test
    public void login() {
        String name = "" + Math.random();
        User u = s.register(name, name);
        createdUsers.add(u);
        assertNotNull(s.login(name, name));    // login OK
        assertNull(s.login(name, name + "123123"));       // login Failure
        assertNull(s.login(name + "123123", name));       // login Failure
    }

    @Test
    public void register() {
        String name = "" + Math.random();
        User u = s.register(name, name);
        createdUsers.add(u);
        assertNotNull(u);
        assertNull(s.register(name, name));         // should not be able to register a user with same name
    }

    @Test
    public void delete() {
        String name = "" + Math.random();
        User u = s.register(name, name);
        System.out.println("DELETING USER: " + u.toString());
        assertNotNull(s.delete(u));             // Returns the deleted user
        assertNull(s.delete(u));                // returns null
    }

    @Test
    public void update() {
        String name = "" + Math.random();
        User u = s.register(name, name);
        createdUsers.add(u);
        u.setCountry("Sweden");
        assertTrue(s.update(u));
        User u2 = s.getUserById(u.getId());
        assertEquals(u.getCountry(), u2.getCountry());
    }

    @Test
    public void getAll() {
        int size = s.getAll().size();
        String name = "" + Math.random();
        User u = s.register(name, name);
        createdUsers.add(u);
        assertEquals(size + 1, s.getAll().size());
    }

    @Test
    public void getUserByName() {
        String name = "" + Math.random();
        User u = s.register(name, name);
        createdUsers.add(u);
        User u2 = s.getUserByName(name);
        assertEquals(u.getUsername(), u2.getUsername());
        assertNull(s.getUserByName(name + "12asdklfjasd"));
    }

    @Test
    public void getUserById() {
        String name = "" + Math.random();
        User u = s.register(name, name);
        createdUsers.add(u);
        assertNotNull(s.getUserById(u.getId()));
        assertNull(s.getUserById(-3));
    }

    @AfterClass
    public static void cleanup() {
        for (User u : createdUsers) {
            s.delete(u);
        }
    }
}
