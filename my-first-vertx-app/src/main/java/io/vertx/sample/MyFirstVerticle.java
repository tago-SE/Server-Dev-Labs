package io.vertx.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;


public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.get("/").handler(rc -> rc.response().end("hello"));
        router.get("/:name").handler(rc -> rc.response()
                .end("hello " + rc.pathParam("name")));
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(7070);
    }

}
