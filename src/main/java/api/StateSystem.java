package api;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class StateSystem {
    public int state = 0;
    public long startTime;
    public long endTime;

    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("state", this.state);
        jsonObject.addProperty("startTime", this.startTime);
        jsonObject.addProperty("endTime", this.endTime);
        return jsonObject;
    }
}
