package beans;

import beans.viewmodels.UserViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

import static clients.ClientUtil.messageClient;
import static clients.ClientUtil.userClient;

@ManagedBean
@SessionScoped
public class UsersBean {

    private List<UserViewModel> users;

    public List<UserViewModel> loadAll() {
        users = UserViewModel.toList(userClient.getAllUsers());
        return users;
    }

}
