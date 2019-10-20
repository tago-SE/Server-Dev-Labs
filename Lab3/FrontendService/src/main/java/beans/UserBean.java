package beans;

import clients.ClientUtil;
import clients.models.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import static clients.ClientUtil.userClient;

@ManagedBean
@SessionScoped
public class UserBean {


    private User user;
    private String newEmail;
    private String newOccupation;
    private String newCountry;

    private boolean isLoggedIn;
    private String username;
    private String password;


    public UserBean() { }


    public void setUser(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public String doUpdate(String var) {
        return null;
    }

    //=======================================================================================================
    //  Login / Register / Logout
    //=======================================================================================================

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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    private void onLogin(User user) {
        isLoggedIn = true;
        setUser(user);
    }

    public String doRegister() {
        if (username != null && username.length() > 0 && password != null && password.length() > 0) {
            User result = userClient.registerUser(new User(password, username));
            if (result != null) {
                onLogin(result);
                return PageControllerBean.moveToIndex();
            }
        }
        return null;
    }

    public String doLogin() {
        User result = userClient.loginUser(username, password);
        if (result != null) {
            onLogin(result);
            return PageControllerBean.moveToIndex();
        }

        return null;
    }

    public String doLogout() {
        isLoggedIn = false;
        user = null;
        return PageControllerBean.moveToIndex();
    }

    //=======================================================================================================
    //  User Detail Management
    //=======================================================================================================


    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewOccupation() {
        return newOccupation;
    }

    public void setNewOccupation(String newOccupation) {
        this.newOccupation = newOccupation;
    }

    public String getNewCountry() {
        return newCountry;
    }

    public void setNewCountry(String newCountry) {
        this.newCountry = newCountry;
    }

    public String update(String column) {
        boolean changed = false;
        switch (column) {
            case "occupation": user.setOccupation(newOccupation); changed = true; break;
            case "email": user.setEmail(newEmail); changed = true; break;
            case "country": user.setCountry(newCountry); changed = true; break;
        }
        if (changed) {
            userClient.updateUser(user);
        }
        return null;
    }
}