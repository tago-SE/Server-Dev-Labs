package beans;

import beans.viewmodels.MessageViewModel;
import clients.models.Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import static clients.ClientUtil.messageClient;

@ManagedBean
@SessionScoped
public class ReadMessageBean {

    private MessageViewModel message;

    public MessageViewModel getMessage() {
        return message;
    }

    public void setMessage(MessageViewModel message) {
        this.message = message;
        read();
    }

    public void read() {
        Message toUpdate = new Message();
        toUpdate.setId(message.getId());
        toUpdate.setSubject(message.getSubject());
        toUpdate.setReceiver(message.getReceiver());
        toUpdate.setSender(message.getSender());
        toUpdate.setTimestamp(message.getTimestamp());
        toUpdate.setMessage(message.getMessage());
        toUpdate.setUnread(false); // Message has been read
        messageClient.updateMessage(toUpdate);
    }

    public void delete() {
        messageClient.deleteMessage(new Message(message.getId()));
        message = null;
    }
}
