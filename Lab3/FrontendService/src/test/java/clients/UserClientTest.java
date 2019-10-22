package clients;

import clients.models.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserClientTest {

    private static UserClient client = ClientUtil.userClient;

    @Test
    public void registerUser() {
        String name = "" + Math.random();
        User u = client.registerUser(new User(name, name));
        System.out.println(u.toString());
        assertNotNull(u);
        client.deleteUser(u);
    }

}
