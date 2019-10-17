package controllers;

import models.User;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class UserClient {

    public final String URL;
    public final String LOGIN_END_POINT;
    public final String REGISTER_END_POINT;
    public final String UPDATE_END_POINT;
    public final String DELETE_END_POINT;

    // @TODO: Work in progress https://www.javaguides.net/2019/02/spring-resttemplate-spring-rest-client-get-post-put-delete-example.html

    public UserClient(String host, int port) {
        URL = "http://" + host + ":" + port + "/users";
        LOGIN_END_POINT = URL + "/login";
        REGISTER_END_POINT = URL + "/register";
        UPDATE_END_POINT = URL + "/update/{id}";
        DELETE_END_POINT = URL + "/delete/{id}";
    }

    public User loginUser(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(LOGIN_END_POINT, new User(username, password), User.class);
    }

    public User registerUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(REGISTER_END_POINT, user, User.class);
    }

    public void updateUser(User user) {
        Map< String, Integer> params = new HashMap<>();
        params.put("id", (int) user.getId());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_END_POINT, user, params);
    }

    public void deleteUser(User user) {
        Map<String, Integer> params = new HashMap<>();
        params.put("id", (int) user.getId());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_END_POINT, params);
    }


}
