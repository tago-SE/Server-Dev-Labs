package clients.models;
import java.io.Serializable;
import java.util.Date;


public class Post implements Serializable {

    private long id;
    private int version;
    private String owner;
    private Date timestamp;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", owner=" + owner +
                ", timestamp='" + timestamp + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
