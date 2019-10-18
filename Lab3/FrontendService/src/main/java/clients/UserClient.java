package clients;

import clients.models.User;
import clients.models.UserList;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserClient {

    private final String URL;
    private final String LOGIN_END_POINT;
    private final String REGISTER_END_POINT;
    private final String UPDATE_END_POINT;
    private final String DELETE_END_POINT;
    private final String GET_USER_BY_ID_END_POINT;
    private final String GET_USER_BY_NAME_END_POINT;
    private final String GET_ALL_USERS_END_POINT;

    public UserClient(String host, int port) {
        URL = "http://" + host + ":" + port + "/users";
        LOGIN_END_POINT = URL + "/login";
        REGISTER_END_POINT = URL + "/register";
        UPDATE_END_POINT = URL + "/update";
        DELETE_END_POINT = URL + "/delete";
        GET_USER_BY_ID_END_POINT = URL + "/get/{id}";
        GET_USER_BY_NAME_END_POINT = URL + "/get/name/{username}";
        GET_ALL_USERS_END_POINT = URL + "/all";
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
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_END_POINT, user);
    }

    public void deleteUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(DELETE_END_POINT, user, User.class);
    }

    public User getUserById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_USER_BY_ID_END_POINT, User.class, id);
    }

    public User getUserByName(String username) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_USER_BY_NAME_END_POINT, User.class, username);
    }

    public List<User> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_ALL_USERS_END_POINT, UserList.class).getUsers();
    }

}
