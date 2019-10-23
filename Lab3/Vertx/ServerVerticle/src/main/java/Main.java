import io.vertx.core.Vertx;

import static io.vertx.core.Vertx.vertx;

public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new GraphDataVerticle());
    }
}
