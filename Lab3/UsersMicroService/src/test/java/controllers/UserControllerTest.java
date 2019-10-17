package controllers;

import models.Dummy;
import models.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    private static final boolean IGNORE_TESTS = false;

    private static final String HOST    = "localhost";
    private static final int PORT       = 8080;

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

        System.out.println(client.LOGIN_END_POINT);
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
        System.out.println(client.REGISTER_END_POINT);
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
        //createdUsers.add(u);
        u.setEmail("tiago@kth.se");
        client.updateUser(u);
    }

    @Test
    public void delete() {
        if (IGNORE_TESTS)
            return;
    }

    @Test
    public void getUserById() {
        if (IGNORE_TESTS)
            return;
    }

    @Test
    public void getUserByName() {
        if (IGNORE_TESTS)
            return;
    }

    @Test
    public void getAll() {
        if (IGNORE_TESTS)
            return;
    }

    @AfterClass
    public static void cleanup() {
        for (User u : createdUsers) {
            s.delete(u);
        }
    }
}
