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

    private List<MessageViewModel> toViewModelList(List<Message> messageList) {
        List<MessageViewModel> newList = new ArrayList<>();
        for (Message m : messageList) {
            newList.add(new MessageViewModel(m));
        }
        return newList;
    }

    public String deleteMessage(MessageViewModel messageViewModel) {
        messageClient.deleteMessage(new Message(messageViewModel.getId()));
        return null;
    }

    public List<MessageViewModel> loadUserInbox(String username) {
        messages = toViewModelList(messageClient.getMessageInbox(username));
        return messages;
    }

}
