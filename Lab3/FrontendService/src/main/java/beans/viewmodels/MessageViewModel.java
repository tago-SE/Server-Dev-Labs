package beans.viewmodels;

import clients.models.Message;

public class MessageViewModel {

    private long id;
    private String sender;
    private String receiver;
    private String timestamp;
    private String subject;
    private String message;
    private boolean unread;

    public MessageViewModel() {

    }

    public MessageViewModel(Message m) {
        this.id = m.getId();
        this.sender = m.getSender();
        this.receiver = m.getReceiver();
        this.timestamp = m.getTimestamp();
        this.subject = m.getSubject();
        this.message = m.getMessage();
        this.unread = m.isUnread();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

}
