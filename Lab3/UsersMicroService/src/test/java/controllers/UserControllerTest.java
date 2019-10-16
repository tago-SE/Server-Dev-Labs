package controllers;

import models.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class UserControllerTest {

    private static final boolean IGNORE_TESTS = true;
    private static final String HOST    = "localhost";
    private static final int PORT       = 8080;

    private static final String URL = "http://" + HOST + ":" + PORT + "/users";

    private static List<User> createdUsers = new ArrayList<>();
    private static UserService s = new UserService();


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

        String uri = URL + "/login/" + name + "/" + name;
        RestTemplate restTemplate = new RestTemplate();
        assertNotNull(restTemplate.getForObject(uri, User.class));
        uri = URL + "/login/" + name + "/" + "asdasdasdasdasd";
        assertNull(restTemplate.getForObject(uri, User.class));
    }

    @Test
    public void registerUser() {
        if (IGNORE_TESTS)
            return;
        String name = "" + Math.random();
        String uri = URL + "/register/" + name + "/" + name;
        RestTemplate restTemplate = new RestTemplate();
        User u = restTemplate.getForObject(uri, User.class);
        createdUsers.add(u);
        assertNotNull(u);
    }

    @Test
    public void update() {
        if (IGNORE_TESTS)
            return;

        String name = "" + Math.random();
        String uri = URL + "/register/" + name + "/" + name;
        RestTemplate restTemplate = new RestTemplate();
        User u = restTemplate.getForObject(uri, User.class);
        createdUsers.add(u);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        u.setEmail("tiago@kth.se");
        uri = URL + "/update/";
        //restTemplate.postForObject(uri, headers);

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
