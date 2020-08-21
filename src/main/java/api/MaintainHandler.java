package api;

import io.vertx.ext.web.RoutingContext;

public class MaintainHandler {
    public static StateSystem stateSystem = new StateSystem();

    public static void editSystemState(RoutingContext rc) {
        String strState = rc.request().getParam("state");
        String strStartTime = rc.request().getParam("st");
        String strEndTime = rc.request().getParam("ed");
        try {
            int state = Integer.parseInt(strState);
            long startTime = Long.parseLong(strStartTime);
            long endTime = Long.parseLong(strEndTime);
            stateSystem.state = state;
            if(stateSystem.state == 1) {
                stateSystem.startTime = startTime;
                stateSystem.endTime = endTime;
            }
            BaseResponse response = new BaseResponse(0, "success");
            rc.response().end(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            BaseResponse response = new BaseResponse(12, "invalid params");
            rc.response().end(response.toString());
        }
    }

    public static void getSytemState(RoutingContext rc) {
        try {
            BaseResponse response = new BaseResponse();
            response.setData(stateSystem.toJsonObject());
            rc.response().end(response.toString());
        }catch (Exception e){
            e.printStackTrace();
            BaseResponse response = new BaseResponse(1, "system error");
            rc.response().end(response.toString());
        }
    }
}
