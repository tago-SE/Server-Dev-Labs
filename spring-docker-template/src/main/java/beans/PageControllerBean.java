package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class PageControllerBean implements Serializable {

    public String moveToRegister() {
        return "register.jsf";
    }

    public String moveToLogin() {
        return "login.jsf";
    }

    public String moveToIndex() { return "index"; }


}