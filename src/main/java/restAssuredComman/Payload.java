package restAssuredComman;


import io.restassured.response.Response;

import java.util.HashMap;

public class Payload {
    public String apiName;
    public String baseUrl;
    public String reqrelateiveURl;
    public HashMap<String, String> reqparameter = new HashMap();
    public HashMap<String, String> reqHeaders = new HashMap();
    public String reqmethod;
    public int reqStatus;
    public String reqbody;
    public String resHeaders;
    public String resStatus;
    public int resStatusCode;
    public String resStrbody;
    public String access_token;
    public Response response;
    public String resTraceId;
    public HashMap<String, String> multiPart = new HashMap();
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";
    public static final String POST_FORM = "POST_FORM";
    public static final String PATCH = "PATCH";
    public static final String YES = "YES";
    public static final String NO = "NO";

    public Payload() {
    }
}

