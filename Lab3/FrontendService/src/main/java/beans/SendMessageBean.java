package beans;

import beans.viewmodels.MessageViewModel;
import clients.models.Message;
import clients.models.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.Date;

import static clients.ClientUtil.messageClient;
import static clients.ClientUtil.userClient;

@ManagedBean
@SessionScoped
public class SendMessageBean {

    private String subject;
    private String message;
    private String from;
    private String to;
    private String timestamp;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void clear() {
        subject = null;
        message = null;
        to = null;
        timestamp = null;
    }

    public void reply(MessageViewModel replyMessage) {
        subject = "Reply: " + replyMessage.getSubject();
        to = replyMessage.getSender();
        message = replyMessage.getMessage() + "\n---\n";
    }

    public String send(String sender) {
        from = sender;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        timestamp = dateFormat.format(new Date());
        Message msg = new Message();
        msg.setSender(from);
        msg.setReceiver(to);
        msg.setMessage(message);
        msg.setSubject(subject);
        msg.setTimestamp(timestamp);
        msg.setUnread(true);
        try {
            User receivingUser = userClient.getUserByName(to);
            if (receivingUser != null) {
                messageClient.createMessage(msg);
                clear();
                return PageControllerBean.moveToIndex();
            } else {
                throw new Exception("Receiver does not exists.");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(),  "???"));
            subject = message = from = to = timestamp = null;
        }
        return null;
    }

}
