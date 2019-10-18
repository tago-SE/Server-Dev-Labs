package clients;

import clients.models.Message;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class MessageClientTest {

    private static MessageClient client = ClientUtil.messageClient;

    private Message createMessage(String sender, String receiver) {
        Message m = new Message();
        m.setSender(sender);
        m.setReceiver(receiver);
        return m;
    }

    private Message createMessageRandomName() {
        String name = "" + Math.random();
        Message m = createMessage(name, name);
        return m;
    }

    @Test
    public void createMessage() {
        Message m = createMessageRandomName();
        m = client.createMessage(m);
        assertNotNull(m);
        assertNotEquals(m.getId(), 0);
        System.out.println(m);
        int count = client.getAllMessages().size();
        client.deleteMessage(m);
        assertEquals(count - 1, client.getAllMessages().size());
    }

    @Test
    public void updateMessage() {
        Message m = createMessageRandomName();
        m = client.createMessage(m);
        m.setSubject("HelloWorld");
       client.updateMessage(m);
       Message updated = client.getMessageById(m.getId());
       assertEquals(m.getSubject(), updated.getSubject());
        client.deleteMessage(m);
    }

    @Test
    public void getMessageInbox() {
        String receiver = "recv_" + Math.random();
        Message m1 = client.createMessage(createMessage(Math.random() + "", receiver));
        Message m2 = client.createMessage(createMessage(Math.random() + "", receiver));
        assertEquals(2, client.getMessageInbox(receiver).size());
        client.deleteMessage(m1);
        client.deleteMessage(m2);
    }
}
