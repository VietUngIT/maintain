package util;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class JsonUtils {
    private static Gson gson = new Gson();
    public static String toJsonString(Object obj) {
        return gson.toJson(obj);
    }

    public static JsonObject toJsonObject(String json) {
        return gson.fromJson(json, JsonObject.class);
    }

    public static Object toJsonObject(String json, Class clazz) {
        return gson.fromJson(json, clazz);
    }

    public static JsonArray toJsonArray(Object obj) {
        try {
            String json = gson.toJson(obj);
            return gson.fromJson(json, JsonArray.class);
        } catch (Exception e) {
            return null;
        }
    }
    public static JsonObject toJsonObject(Object obj) {
        try {
            String json = gson.toJson(obj);
            return gson.fromJson(json, JsonObject.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static int[] toIntArray(JsonArray bet_tai) {
        int[] v = new int[bet_tai.size()];
        for(int i = 0; i < bet_tai.size(); i++){
            v[i] = bet_tai.get(i).getAsInt();
        }
        return v;
    }
}
