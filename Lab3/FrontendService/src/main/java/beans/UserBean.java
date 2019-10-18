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
        User result = userClient.loginUser(username, password);
        if (result != null) {
            onLogin(result);
            return "index.jsp";
        }
        return null; // Something went wrong
    }

    public String doLogin() {
        User u = new User(username, password);
        System.out.println(u.toString());
        User result = userClient.registerUser(u);
        if (result != null) {
            onLogin(result);
            return "index.jsp";
        } else {
            return null; // Something went wrong
        }
    }

    public String doLogout() {
        isLoggedIn = false;
        user = null;
        return "index.jsp";
    }


}