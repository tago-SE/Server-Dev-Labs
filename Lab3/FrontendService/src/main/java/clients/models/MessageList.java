package clients.models;

import java.io.Serializable;
import java.util.List;

/**
 * Wrapper class for list serialization before passing it through springboot.
 */
public class MessageList implements Serializable {

    private List<Message> list;

    public MessageList() {
        // Required empty constructor
    }

    public MessageList(List<Message> list) {
        this.list = list;
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }
}
