package services;

import models.Message;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MessageServiceTest {

    private static MessageService s = new MessageService();
    private static List<Message> createdMessages = new ArrayList<>();   // List containing all created messages

    // These test can fail as there is no sanity check on which names have been used when a number is randomized.
    // So however unlikely that is to occur, do rerun the tests in case it fails before starting the debug process.

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
    public void insert() {
        Message m = s.insert(createMessageRandomName());
        createdMessages.add(m);
        assertNotNull(m);
    }

    @Test
    public void delete() {
        Message m = s.insert(createMessageRandomName());
        s.delete(m);
        assertNull(s.getMessageById(m.getId()));
    }

    @Test
    public void update() {
        Message m = s.insert(createMessageRandomName());
        m.setSender("AAAA");
        createdMessages.add(m);
        Message m2 = s.update(m);
        assertEquals("AAAA", m2.getSender());
    }

    @Test
    public void getAll() {
        int size = s.getAll().size();
        Message m = s.insert(createMessageRandomName());
        createdMessages.add(m);
        assertEquals(size + 1, s.getAll().size());
    }

    @Test
    public void getUserInbox() {
        String receiver = "recv_" + Math.random();
        Message m = createMessage(Math.random() + "", receiver);
        s.insert(m);
        createdMessages.add(m);
        m = createMessage(Math.random() + "", receiver);
        s.insert(m);
        createdMessages.add(m);
        assertEquals(2, s.getUserInbox(receiver).size());
    }

    @Test
    public void getMessageById() {
        Message m = s.insert(createMessageRandomName());
        createdMessages.add(m);
        assertNotNull(s.getMessageById(m.getId()));
        assertNull(s.getMessageById(-3));
    }

    @AfterClass
    public static void cleanup() {
        for (Message m : createdMessages) {
            s.delete(m);
        }
    }
}
