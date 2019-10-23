package io.vertx.sample;

import db.UserDiagramDao;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import models.UserDiagram;

import java.util.ArrayList;
import java.util.List;

public class DiagramRestMicroservice extends AbstractVerticle {

    private UserDiagramDao userDiagramDao;

    public DiagramRestMicroservice(UserDiagramDao userDiagramDao) {
        this.userDiagramDao = userDiagramDao;
    }

    @Override
    public void start() {

        // Create a router object.
        Router router = Router.router(vertx);

        router.route("/api/diagrams*").handler(BodyHandler.create());
        router.post("/api/diagrams").handler(this::insert);
        router.delete("/api/diagrams/delete/:id").handler(this::delete);
        router.get("/api/diagrams/get/:id").handler(this::get);
        router.get("/api/diagrams/get/user/:username").handler(this::getByUser);


        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(7070);
    }

    private void getByUser(RoutingContext rc) {
        String username = rc.request().getParam("username");
        if (username == null) {
            rc.response().setStatusCode(400).end();
        } else {
            List<UserDiagram> diagrams = userDiagramDao.getByUser(username);
            rc.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(diagrams));
        }
    }

    private void insert(RoutingContext rc) {
        final UserDiagram diagram = Json.decodeValue(rc.getBodyAsString(),
                UserDiagram.class);
        userDiagramDao.insert(diagram);
        rc.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(diagram));
    }

    private void delete(RoutingContext rc) {
        String id = rc.request().getParam("id");
        if (id == null) {
            rc.response().setStatusCode(400).end();
        } else {
            userDiagramDao.delete(id);
            rc.response().setStatusCode(204).end();
        }
    }

    private void get(RoutingContext rc) {
        final String id = rc.request().getParam("id");
        if (id == null) {
            rc.response().setStatusCode(400).end();
        } else {
            UserDiagram diagram = userDiagramDao.get(id);
            if (diagram == null) {
                rc.response().setStatusCode(404).end();
            } else {
                rc.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(diagram));
            }
        }
    }

}
