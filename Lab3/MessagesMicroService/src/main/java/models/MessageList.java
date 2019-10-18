package models;

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

    public MessageList(List<Message> users) {
        this.list = users;
    }

    public List<Message> getMessages() {
        return list;
    }

    public void setMessages(List<Message> users) {
        this.list = users;
    }
}
