package beans;

import beans.viewmodels.PostViewModel;
import beans.viewmodels.UserViewModel;
import clients.models.Message;
import clients.models.Post;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static clients.ClientUtil.messageClient;
import static clients.ClientUtil.postClient;

@ManagedBean
@SessionScoped
public class ProfileBean {

    private UserViewModel user;
    private String postText;

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void submitPost() {
        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Post post = new Post();
        post.setMessage(postText);
        post.setOwner(user.getUsername());
        post.setTimestamp(new Date());
        postClient.createPost(post);
    }

    public List<PostViewModel> loadAllPosts() {
        return PostViewModel.toList(postClient.getPostByUserName(user.getUsername()));
    }
}
