package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "T_Message")
@NamedQueries({
        @NamedQuery(name = "Message.findAll", query = "SELECT m FROM  Message m"),
        @NamedQuery(name = "Message.findInbox", query = "SELECT m FROM  Message m WHERE m.receiver = :receiver"),
        @NamedQuery(name = "Message.findOutbox", query = "SELECT m FROM  Message m WHERE m.sender = :sender"),
})
public class Message implements EntityInt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private long version;

    @Column
    private String sender;

    @Column
    private String receiver;

    @Column
    private String timestamp;

    @Column
    private String subject;

    @Column
    private String message;

    @Column
    private boolean unread;

    public Message() { }

    public Message(long id) {
        this.id = id;
    }

    public static Query createGetAllQuery(EntityManager em) {
        return em.createNamedQuery("Message.findAll");
    }

    public static Query createInboxQuery(EntityManager em, String username) {
        return em.createNamedQuery("Message.findInbox").setParameter("receiver", username);
    }

    public static Query createOutboxQuery(EntityManager em, String username) {
        return em.createNamedQuery("Message.findOutbox").setParameter("sender", username);
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getVersion() {
        return version;
    }

    @Override
    public void update(EntityManager em, EntityInt source) {
        Message m = (Message) source;
        this.sender = m.sender;
        this.receiver = m.receiver;
        this. timestamp = m.timestamp;
        this.subject = m.subject;
        this.message = m.message;
        this.unread = m.unread;
    }

    @Override
    public void delete(EntityManager em) {
        // No relationships to unlink
    }

    public void setVersion(long version) {
        this.version = version;
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
