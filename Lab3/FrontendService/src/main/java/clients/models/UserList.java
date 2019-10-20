package clients.models;

import java.io.Serializable;
import java.util.List;

/**
 * Wrapper class for list serialization before passing it through springboot.
 */
public class UserList implements Serializable {

    private List<User> list;

    public UserList() {
        // Required empty constructor
    }

    public UserList(List<User> list) {
        this.list = list;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
