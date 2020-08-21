package api;

import config.MaintainConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public class MaintainHttpServer extends AbstractVerticle {
    private HttpServer server;

    @Override
    public void start() {
        Router router = Router.router(vertx);
        allowAccessControlOrigin(router);
        router.get("/test").handler(this::test);
//        router.get("/admin/editsystemstate").handler(MaintainConfig::whilist).handler(MaintainHandler::editSystemState);
        router.get("/admin/editsystemstate").handler(MaintainHandler::editSystemState);
        router.get("/hotupdatestate").handler(MaintainConfig::checkKeySecret).handler(MaintainHandler::editSystemState);
        router.get("/systemstate").handler(MaintainHandler::getSytemState);
        server = vertx.createHttpServer().requestHandler(router).listen(MaintainConfig.config.get("port").getAsInt());
    }

    public void allowAccessControlOrigin(Router router) {
        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("*");
        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);
        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));
    }

    private void test(RoutingContext rc) {
        rc.response().end("OK");
    }

    @Override
    public void stop() {
        if (server != null) server.close();
    }
}
