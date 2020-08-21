package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.vertx.core.http.HttpServerRequest;
import lombok.Data;

import java.io.*;

@Data
public class ConfigUtils {
    public static JsonObject getConfig(String filePath, boolean relative) {
        String path = "";
        if(relative){
            path = System.getProperty("user.dir");
        }
        File file = new File(path + filePath);
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        try {
            Reader r = new InputStreamReader( new FileInputStream(file),"UTF-8");
            reader = new BufferedReader(r);
            String text = null;
            while ((text = reader.readLine()) != null) {
                contents.append(text).append(System.getProperty("line.separator"));
            }
            JsonObject config = JsonUtils.toJsonObject(contents.toString());
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getIp(HttpServerRequest request) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(request.remoteAddress()));
        System.out.println(gson.toJson(request.localAddress()));
        String ip = request.getHeader("X-Real-IP");
        if (ip == null) {
            ip = "NO_IP";
        }

        return ip;
    }
}