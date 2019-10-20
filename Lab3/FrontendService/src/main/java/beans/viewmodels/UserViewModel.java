package beans.viewmodels;

import clients.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel {

    private long id;
    private long version;
    private String username;
    private String password;
    private String email;
    private String country;
    private String occupation;

    public UserViewModel() {

    }

    public UserViewModel(User user) {

        this.id = user.getId();
        this.version = user.getVersion();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.country = user.getCountry();
        this.occupation = user.getOccupation();
    }

    public static List<UserViewModel> toList(List<User> allUsers) {
        List<UserViewModel> newList = new ArrayList<>();
        for (User u : allUsers) {
            newList.add(new UserViewModel(u));
        }
        return newList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
