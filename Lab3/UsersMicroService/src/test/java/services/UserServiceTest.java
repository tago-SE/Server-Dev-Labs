package services;

import models.User;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class UserServiceTest {

    @Test
    public void login() {
    }

    @Test
    public void register() {
        UserService s = new UserService();
        String name = "" + Math.random();
        assertNotNull(s.register(name, name));
        assertNull(s.register(name, name));
    }
}
