package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import models.UserDiagram;
import models.UserDiagramPoint;

import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class handling incoming data updates and sends it to all subscribed clients
 */
public class LiveDataMicroservice extends AbstractVerticle {

    private static final Logger _logger = LoggerFactory.getLogger(LiveDataMicroservice.class);

    // The name used on the client-side to link up with the event-bus
    // Example: data.1  - 1 being a ID for that particular session.

    private static final String PUBLIC_DATA_HANDLER_NAME = "data";

    private final int PORT = 5050;

    private StaticHandler staticHandler() {
        return StaticHandler.create()
                .setCachingEnabled(false);
    }

    private ErrorHandler errorHandler() {
        return ErrorHandler.create();
    }

    private SockJSHandler eventBusHandler() {
        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddressRegex(PUBLIC_DATA_HANDLER_NAME + "\\.[0-9]+"));
        return SockJSHandler.create(vertx).bridge(options, event -> {
            if (event.type() == BridgeEventType.SOCKET_CREATED) {
                _logger.info("A socket was created");
            }
            event.complete(true);
        });
    }

    // Precondition: the args are split in numbers separated by ','
    // Example: args = { "5,3", "3,4" }
    private List<UserDiagramPoint> parseToNodes(String[] args) {
        List<UserDiagramPoint> resultList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            String[] s = args[i].split(",");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            resultList.add(new UserDiagramPoint(x, y));
        }
        return resultList;
    }

    private void handleDataUploads(RoutingContext context) {
        String id = context.request().getParam("id");
        String recv = context.getBodyAsJson().getString("data");
        _logger.info("Received: " + recv);
        UserDiagram diagram = new UserDiagram();
        diagram.setName("pie");
        diagram.setType("pie");
        diagram.setTimestamp(new Date());
        List<UserDiagramPoint> points = parseToNodes(recv.split(" "));
        _logger.info("Points: " + points);
        diagram.setDataPoints(points);

        context.vertx().eventBus().publish(PUBLIC_DATA_HANDLER_NAME + "." + id, Json.encodePrettily(diagram));

    }

    private Router dataUpdateApiRouter() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route().consumes("application/json");
        router.route().produces("application/json");
        router.patch("/data/:id").handler(this::handleDataUploads);
        return router;
    }

    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(vertx);
        router.route().handler(CorsHandler.create("*")
                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
                .allowedMethod(io.vertx.core.http.HttpMethod.PATCH)
                .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
                .allowedHeader("Access-Control-Request-Method")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Headers")
                .allowedHeader("Content-Type"));
        router.route("/eventbus/*").handler(eventBusHandler());
        router.mountSubRouter("/api", dataUpdateApiRouter());
        router.route().failureHandler(errorHandler());
        router.route().handler(staticHandler());
        vertx.createHttpServer().requestHandler(router::accept).listen(PORT);
    }
}
