package beans.viewmodels;

import clients.models.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostViewModel {

    private long id;
    private int version;
    private String owner;
    private Date timestamp;
    private String message;

    public PostViewModel(Post p) {
        this.id = p.getId();
        this.version = p.getVersion();
        this.owner = p.getOwner();
        this.timestamp = p.getTimestamp();
        this.message = p.getMessage();
    }

    public static List<PostViewModel> toList(List<Post> list) {
        List<PostViewModel> newList = new ArrayList<>();
        for (Post p : list) {
            newList.add(new PostViewModel(p));
        }
        return newList;
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
}
