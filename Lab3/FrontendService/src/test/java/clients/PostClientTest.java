package clients;

import clients.models.Post;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

public class PostClientTest {

    private static PostClient client = ClientUtil.postClient;

    private Post createPostForUser(String name) {
        Post p = new Post();
        p.setOwner(name);
        return p;
    }

    private Post createRandomPost() {
        return createPostForUser(Math.random() + "");
    }

    @Test
    public void createPost() {
        Post p = createRandomPost();
        p = client.createPost(p);
        assertNotNull(p);
        assertNotEquals(p.getId(), 0);
        System.out.println(p);
        client.deletePost(p);
        assertNull(client.getPostById(p.getId()));
    }

    @Test
    public void updatePost() {
        Post p = createRandomPost();
        p = client.createPost(p);
        p.setMessage("Hello");
        client.updatePost(p);
        Post p2 = client.getPostById(p.getId());
        assertEquals(p.getMessage(), p2.getMessage());
        client.deletePost(p);
    }

    @Test
    public void getPostsByName() {
        String poster = "poster_" + Math.random();
        Post p1 = client.createPost(createPostForUser(poster));
        Post p2 = client.createPost(createPostForUser(poster));
        List<Post> posts = client.getPostByUserName(poster);
        System.out.println(posts);
        assertEquals(2, client.getPostByUserName(poster).size());
        client.deletePost(p1);
        client.deletePost(p2);
    }
}
