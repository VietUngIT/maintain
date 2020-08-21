package api;

import config.MaintainConfig;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.apache.log4j.PropertyConfigurator;
import util.Debug;

public class MaintainLaucher {
    public static Vertx vertx;
    public static void main(String[] args) {
        try {
            PropertyConfigurator.configure("config/log4j.properties");
            MaintainConfig.getConfig();
            startHttpServer();
            shutdownHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startHttpServer() {
        int procs = Runtime.getRuntime().availableProcessors();
        VertxOptions vxOptions = new VertxOptions()
                .setBlockedThreadCheckInterval(30000);

        vertx = Vertx.vertx(vxOptions);
        DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setWorker(true).setWorkerPoolSize(procs*2);
        vertx.deployVerticle(MaintainHttpServer.class.getName(),
                deploymentOptions.setInstances(procs*2), event -> {
                    if (event.succeeded()) {
                        Debug.info("Your Vert.x application is started!");
                    } else {
                        Debug.error("Unable to start your application", event.cause());
                    }
                });
    }
    public static void shutdownHook(){
        Debug.info("TXLiveLauncher is power up");
        Runtime.getRuntime().addShutdownHook(new Thread(
                ()->{
                    Debug.info("TXLiveLauncher is shutdown");
                }
        ));
    }
}
