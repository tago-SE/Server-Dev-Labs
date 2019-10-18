package services;

import models.Post;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PostServiceTest {

    private static PostService s = new PostService();
    private static List<Post> createdPosts = new ArrayList<>();   // List containing all created posts

    private Post createPostForUser(String name) {
        Post p = new Post();
        p.setOwner(name);
        return p;
    }

    private Post createRandomPost() {
        return createPostForUser(Math.random() + "");
    }

    @Test
    public void insert() {
        Post p = s.insert(createRandomPost());
        createdPosts.add(p);
        assertNotNull(p);
    }

    @Test
    public void delete() {
        Post p = s.insert(createRandomPost());
        s.delete(p);
        assertNull(s.getPostById(p.getId()));;
    }

    @Test
    public void update() {
        Post p = s.insert(createRandomPost());
        createdPosts.add(p);
        p.setMessage("Hello friend");
        Post p2 = s.update(p);
        assertEquals(p.getMessage(), p2.getMessage());
    }

    @Test
    public void getUserPosts() {
        String poster = "poster_" + Math.random();
        Post p1 = createPostForUser(poster);
        s.insert(p1);
        createdPosts.add(p1);
        Post p2 = createPostForUser(poster);
        s.insert(p2);
        createdPosts.add(p2);
        assertEquals(2, s.getUserPosts(poster).size());
    }

    @Test
    public void getPostById() {
        Post p = s.insert(createRandomPost());
        createdPosts.add(p);
        assertNotNull(s.getPostById(p.getId()));
        assertNull(s.getPostById(-3));
    }

    @AfterClass
    public static void cleanup() {
        for (Post p : createdPosts) {
            s.delete(p);
        }
    }
}
