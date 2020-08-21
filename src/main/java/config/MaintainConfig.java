package config;

import api.BaseResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.vertx.ext.web.RoutingContext;
import util.ConfigUtils;

public class MaintainConfig {
    private static final String CONFIG_FILE = "/config/maintain.json";
    public static JsonObject config;
    public static void getConfig(){
        config = ConfigUtils.getConfig(CONFIG_FILE, true);
    }

    public static void whilist(RoutingContext rc) {
        try {
            getConfig();
            String ip = ConfigUtils.getIp(rc.request());
            System.out.println(ip);
            if (config != null) {
                boolean check = checkIPAccept(ip);
                if (check) {
                    rc.next();
                } else {
                    rc.response().end(new BaseResponse(3, "Invalid IP").toString());
                }
            } else {
                rc.response().end(new BaseResponse(2, "Get config fail").toString());
            }
        } catch (Exception e) {
            rc.response().end(new BaseResponse(1, "System Error").toString());
            e.printStackTrace();
        }
    }

    public static void checkKeySecret(RoutingContext rc) {
        try {
            String key = rc.request().getParam("key");
            if (key != null) {
                if (key.trim().equalsIgnoreCase("hjdjashdjasho")) {
                    rc.next();
                } else {
                    rc.response().end(new BaseResponse(4, "denied access").toString());
                }
            } else {
                rc.response().end(new BaseResponse(4, "denied access").toString());
            }
        } catch (Exception e) {
            rc.response().end(new BaseResponse(1, "System Error").toString());
            e.printStackTrace();
        }
    }

    public static boolean checkIPAccept(String ip) {
        if(config!=null) {
            JsonArray jsonArray = config.getAsJsonArray("whilistIP");
            if (jsonArray != null) {
                if(jsonArray.size()==0) {
                    return true;
                } else {
                    for (int i=0; i<jsonArray.size(); i++) {
                        if(ip.trim().equalsIgnoreCase(jsonArray.get(i).getAsString().trim())){
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
