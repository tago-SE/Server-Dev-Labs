package clients;

public class ClientUtil {

    public static UserClient userClient = new UserClient("192.168.99.100", 9091);
    public static MessageClient messageClient = new MessageClient("192.168.99.100", 9092);
    public static PostClient postClient = new PostClient("192.168.99.100", 9093);

}
