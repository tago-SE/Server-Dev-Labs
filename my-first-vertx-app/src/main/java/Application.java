import io.vertx.core.Vertx;
import io.vertx.sample.MyFirstVerticle;

public class Application {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyFirstVerticle());
    }

}
