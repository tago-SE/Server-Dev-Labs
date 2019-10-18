package models;

import java.io.Serializable;
import java.util.List;

/**
 * Wrapper class for list serialization before passing it through springboot.
 */
public class PostList implements Serializable {

    private List<Post> list;

    public PostList() {
        // Required empty constructor
    }

    public PostList(List<Post> list) {
        this.list = list;
    }

    public List<Post> getMessages() {
        return list;
    }

    public void setMessages(List<Post> list) {
        this.list = list;
    }
}
