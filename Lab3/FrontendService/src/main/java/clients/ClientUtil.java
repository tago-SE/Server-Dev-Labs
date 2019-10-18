package clients;

public class ClientUtil {

    public static final UserClient userClient = new UserClient("192.168.99.100", 9091);
    public static final MessageClient messageClient = new MessageClient("192.168.99.100", 9092);

}
