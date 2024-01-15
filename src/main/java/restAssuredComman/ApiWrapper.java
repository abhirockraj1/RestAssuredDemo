package restAssuredComman;

import java.util.HashMap;

public class ApiWrapper extends Payload{
    ApiUtils api  = new ApiUtils();
    public String callGetApi(String url){

        reqrelateiveURl = url;
        reqmethod = GET;
        api.CallMethod(this);
        return resStrbody;
    }
    public String callPostApi(String url ,String reqBody, HashMap<String, String> headdersForSeesion) {
        reqrelateiveURl = url;
        reqmethod = POST;
        reqbody = reqBody;
        reqparameter = new HashMap<String, String>();
        reqHeaders = headdersForSeesion;
        api.CallMethod(this);
        System.out.print(resStrbody);
        return resStrbody;
    }
}