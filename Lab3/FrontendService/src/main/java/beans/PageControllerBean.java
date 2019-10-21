package beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class PageControllerBean implements Serializable {

    public static String moveToRegister() {
        return "register.jsf";
    }

    public static String moveToLogin() {
        return "login.jsf";
    }

    public static String moveToInbox() { return "inbox.jsf"; }

    public static String moveToMessageRead() { return "message_read.jsf"; }

    public static String moveToMessageWrite() { return "message.jsf"; }

    public static String moveToIndex() { return "index"; }

    public String moveToProfile() {
        return "profile.jsf";
    }

    public String canvasURL() {
        return "drawer.html";
    }


}
