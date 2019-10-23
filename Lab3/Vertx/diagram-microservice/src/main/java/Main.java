import db.UserDiagramDao;
import io.vertx.core.Vertx;
import io.vertx.sample.DiagramRestMicroservice;

public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new DiagramRestMicroservice(new UserDiagramDao("192.168.99.100:32768", "server_lab_3")));
    }
}
