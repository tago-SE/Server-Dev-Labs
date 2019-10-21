
/*
    Verticles are pieces of code that Vert.x engine executes. The toolkit provides us many abstract verticle class,
     which can be extended, and implemented as we want to. Being polyglot, verticles can be written in any of the '
     supported languages. An application would typically be composed of multiple verticles running in the same Vert.x
     instance and communicate with each other using events via the event bus.
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class HelloVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {
        System.out.println("Welcome to vertx");
    }
}
