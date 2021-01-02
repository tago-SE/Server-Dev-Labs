import db.UserDiagramDao;
import io.vertx.core.Vertx;
import vertx.DiagramRestMicroservice;
import vertx.LiveDataMicroservice;

public class Main {

    private static final String DB_HOST_ADDRESS = "192.168.99.100:32770";
    private static final String DB_NAME = "server_lab_3";

    public static void main(String[] args) {
        UserDiagramDao dao = new UserDiagramDao(
                args.length > 0 ? args[0] : DB_HOST_ADDRESS,
                args.length > 1 ? args[1] : DB_NAME
        );
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new DiagramRestMicroservice(dao));
        vertx.deployVerticle(new LiveDataMicroservice());
    }


}
