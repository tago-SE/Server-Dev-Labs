package controllers;

import models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;


public class UserControllerTest {

    private static final String HOST    = "localhost";
    private static final int PORT       = 8080;

    private static final String URL = "http://" + HOST + ":" + PORT + "/users";


    @Test
    public void index() {
        String result = new RestTemplate().getForObject(URL, String.class);
        assertEquals(result, "Users");
    }

    @Test
    public void loginUser() {
        String uri = URL + "/login/tiago/tiago";
        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.getForObject(uri, User.class);
        System.out.println("RESULT : " + result);
    }

  //  @Test
    public void registerUser() {
    }

  //  @Test
    public void update() {
    }

  //  @Test
    public void delete() {
    }

    //@Test
    public void getUserById() {
    }

    //@Test
    public void getUserByName() {
    }

    //@Test
    public void getAll() {
    }
}
