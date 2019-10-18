package clients;

import clients.models.User;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class UserClientTest {

    private static UserClient client = ClientUtil.userClient;

    @Test
    public void registerUser() {
        String name = "" + Math.random();
        User u = client.registerUser(new User(name, name));
        System.out.println(u.toString());
        assertNotNull(u);
    }

}
