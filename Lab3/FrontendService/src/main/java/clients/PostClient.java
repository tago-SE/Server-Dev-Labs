package clients;

import clients.models.Post;
import clients.models.PostList;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PostClient {

    private final String URL;
    private final String CREATE_END_POINT;
    private final String UPDATE_END_POINT;
    private final String DELETE_END_POINT;
    private final String GET_BY_ID_END_POINT;
    private final String GET_POST_BY_USER_END_POINT;

    public PostClient(String host, int port) {
        URL = "http://" + host + ":" + port + "/posts";
        CREATE_END_POINT = URL + "/create";
        UPDATE_END_POINT = URL + "/update";
        DELETE_END_POINT = URL + "/delete";
        GET_BY_ID_END_POINT = URL + "/get/{id}";
        GET_POST_BY_USER_END_POINT = URL + "/get/user/{username}";
    }

    public Post createPost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(CREATE_END_POINT, post, Post.class);
    }

    public void updatePost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_END_POINT, post);
    }

    public void deletePost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(DELETE_END_POINT, post, Post.class);
    }

    public Post getPostById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_BY_ID_END_POINT, Post.class, id);
    }

    public List<Post> getPostByUserName(String username) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_POST_BY_USER_END_POINT, PostList.class, username).getList();
    }
    
}
