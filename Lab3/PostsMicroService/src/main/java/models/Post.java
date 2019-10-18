package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_Post")

@NamedQueries({
        @NamedQuery(name = "Post.findAllPostsByUser", query = "SELECT p FROM  Post p WHERE p.owner = :owner")
})
public class Post implements EntityInt  {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private int version;

    @Column
    private String owner;

    @Column
    private Date timestamp;

    @Column
    private String message;

    public Post() {
        timestamp = new Date();
    }

    public Post(long id) {
        this.id = id;
        timestamp = new Date();
    }

    public Post(String owner, String message, Date timestamp) {
        this.owner = owner;
        this.timestamp = timestamp;
        this.message = message;
    }

    public static Query createUserPostQuery(EntityManager em, String username) {
        return em.createNamedQuery("Post.findAllPostsByUser").setParameter("owner", username);
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void update(EntityManager em, EntityInt source) {
        Post p = (Post) source;
        this.owner = p.owner;
        this.timestamp = p.timestamp;
        this.message = p.message;
    }

    @Override
    public void delete(EntityManager em) {

    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", owner=" + owner +
                ", timestamp='" + timestamp + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
