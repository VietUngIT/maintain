package api;

import lombok.Data;
import util.JsonUtils;

@Data
public class BaseResponse {
    int e = 0;
    String message = "success";
    Object data;

    public BaseResponse(int error, String message){
        this.e = error;
        this.message = message;
    }

    public BaseResponse(){

    }

    public String toString(){
        return JsonUtils.toJsonString(this);
    }

    public boolean isSuccess() {
        return e == 0;
    }
}
