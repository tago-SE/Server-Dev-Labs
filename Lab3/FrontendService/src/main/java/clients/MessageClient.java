package clients;

import clients.models.Message;
import clients.models.MessageList;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MessageClient {

    private final String URL;
    private final String CREATE_END_POINT;
    private final String UPDATE_END_POINT;
    private final String DELETE_END_POINT;
    private final String GET_BY_ID_END_POINT;
    private final String GET_INBOX_END_POINT;
    private final String GET_ALL_END_POINT;

    public MessageClient(String host, int port) {
        URL = "http://" + host + ":" + port + "/messages";
        CREATE_END_POINT = URL + "/create";
        UPDATE_END_POINT = URL + "/update";
        DELETE_END_POINT = URL + "/delete";
        GET_BY_ID_END_POINT = URL + "/get/{id}";
        GET_INBOX_END_POINT = URL + "/get/inbox/{username}";
        GET_ALL_END_POINT = URL + "/all";
    }

    public Message createMessage(Message message) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(CREATE_END_POINT, message, Message.class);
    }

    public void updateMessage(Message message) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(UPDATE_END_POINT, message);
    }

    public void deleteMessage(Message message) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(DELETE_END_POINT, message, Message.class);
    }

    public Message getMessageById(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_BY_ID_END_POINT, Message.class, id);
    }

    public List<Message> getMessageInbox(String username) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_INBOX_END_POINT, MessageList.class, username).getList();
    }

    public List<Message> getAllMessages() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(GET_ALL_END_POINT, MessageList.class).getList();
    }
}
