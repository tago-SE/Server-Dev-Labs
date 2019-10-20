package beans;

import beans.viewmodels.MessageViewModel;
import clients.models.Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

import static clients.ClientUtil.messageClient;

@ManagedBean
@SessionScoped
public class InboxBean {

    private List<MessageViewModel> messages;

    public String deleteMessage(MessageViewModel messageViewModel) {
        messageClient.deleteMessage(new Message(messageViewModel.getId()));
        return null;
    }

    public List<MessageViewModel> loadUserInbox(String username) {
        messages = MessageViewModel.toList(messageClient.getMessageInbox(username));
        return messages;
    }

}
