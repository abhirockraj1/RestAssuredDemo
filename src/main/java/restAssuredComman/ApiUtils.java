package restAssuredComman;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.StringReader;

public class ApiUtils {
    private static final Logger logger = LogManager.getLogger(ApiUtils.class);

    public static boolean isValidJSON(String json) {
        ObjectMapper mapper = (new ObjectMapper()).enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);

        try {
            mapper.readTree(json);
            return true;
        } catch (JacksonException var3) {
            return false;
        }
    }
    public static String getPrettyJsonString(String json) {
        try {
            if (json != null && !json.isEmpty() && isValidJSON(json)) {
                ObjectMapper mapperObj = new ObjectMapper();
                return mapperObj.writerWithDefaultPrettyPrinter().writeValueAsString(jsonStringToObject(json, JsonNode.class));
            }
        } catch (Exception var2) {
            logger.error(var2);
        }

        return json;
    }
    public static String objectToJsonString(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // convert user object to json string and return it
            return mapper.writeValueAsString(obj);
        }
        catch (JsonGenerationException | JsonMappingException e) {
            // catch various errors
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static <T> T jsonStringToObject(String resStrbody, Class<T> obj) {
        if (resStrbody == null) {
            return null;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                return mapper.readValue(new StringReader(resStrbody), obj);
            } catch (Throwable var5) {
                logger.error(var5);
                return null;
            }
        }
    }

    public int CallMethod(Payload payload) {
        int statusCode = 0;
        switch (payload.reqmethod) {
            case "GET":
                payload = this.GetMethod(payload);
                break;
            case "POST":
                payload = this.PostMethod(payload);
                break;
            case "PUT":
                payload = this.PutMethod(payload);
                break;
            case "DELETE":
                payload = this.DeleteMethod(payload);
                break;
            case "POST_FORM":
                payload = this.PostMethodWithFormData(payload);
                break;
            case "PATCH":
                payload = this.PatchMethod(payload);
                break;
            default:
                logger.error("Invalid API method");
        }

        statusCode = payload.resStatusCode;
        return statusCode;
    }
    public Payload GetMethod(Payload payload) {
        try {
            logger.info("Get Method");
            logger.info(RestAssured.baseURI + payload.reqrelateiveURl);
            Response response = RestAssured.given().params(payload.reqparameter).headers(payload.reqHeaders).when().get(payload.reqrelateiveURl, new Object[0]);
            String strResponse = response.asString();
            payload.resStrbody = getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return payload;
    }
    public Payload PostMethod(Payload payload) {
        try {
//            logger.info("Post Method");
//            RestAssured.baseURI = this.baseUrl;
            logger.info(RestAssured.baseURI + payload.reqrelateiveURl);
            Response response = null;
            RequestSpecification requestSpecification = RestAssured.given();
            if (payload.reqparameter.size() > 0) {
                requestSpecification.queryParams(payload.reqparameter);
            }

            response = (Response)requestSpecification.headers(payload.reqHeaders).body(payload.reqbody).when().post(payload.reqrelateiveURl, new Object[0]);
            String strResponse = response.asString();
            payload.resStrbody = getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        return payload;
    }

    public Payload PatchMethod(Payload payload) {
        try {
            logger.info("Patch Method");
            logger.info(RestAssured.baseURI + payload.reqrelateiveURl);
            Response response = null;
            if (payload.reqbody != null) {
                response = RestAssured.given().headers(payload.reqHeaders).body(payload.reqbody).when().patch(payload.reqrelateiveURl);
            } else {
                response = RestAssured.given().headers(payload.reqHeaders).when().patch(payload.reqrelateiveURl);
            }

            String strResponse = response.asString();
            payload.resStrbody = getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return payload;
    }
    public static boolean contains(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        } else if (str1 != null && str2 != null) {
            return str1.toLowerCase().trim().contains(str2.toLowerCase().trim()) || str2.toLowerCase().trim().contains(str1.toLowerCase().trim());
        } else {
            return false;
        }
    }
    public Payload PostMethodWithFormData(Payload payload) {
        try {
            logger.info("Post Method");
//            RestAssured.baseURI = this.baseUrl;
            logger.info(RestAssured.baseURI + payload.reqrelateiveURl);
            Response response = null;
            RequestSpecification request = RestAssured.given().headers(payload.reqHeaders).contentType("multipart/form-data").request();
            payload.multiPart.forEach((key, value) -> {
                if (contains(key, "file")) {
                    request.multiPart("file", new File(value));
                } else {
                    request.multiPart(key, value);
                }

            });
            response = (Response)request.when().post(payload.reqrelateiveURl, new Object[0]);
            String strResponse = response.asString();
            payload.resStrbody = getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return payload;
    }
    public Payload PutMethod(Payload payload) {
        try {
            logger.info("Put Method");
//            RestAssured.baseURI = this.baseUrl;
            logger.info(RestAssured.baseURI + payload.reqrelateiveURl);
            Response response = (Response)RestAssured.given().params(payload.reqparameter).headers(payload.reqHeaders).body(payload.reqbody).when().put(payload.reqrelateiveURl, new Object[0]);
            String strResponse = response.asString();
            payload.resStrbody = getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return payload;
    }

    public Payload DeleteMethod(Payload payload) {
        try {
            logger.info("Delete Method");
//            RestAssured.baseURI = this.baseUrl;
            logger.info(payload.toString());
            Response response = (Response)RestAssured.given().params(payload.reqparameter).headers(payload.reqHeaders).body(payload.reqbody).when().delete(payload.reqrelateiveURl, new Object[0]);
            String strResponse = response.asString();
            payload.resStrbody = getPrettyJsonString(strResponse);
            payload.resStatusCode = response.getStatusCode();
            payload.response = response;
        } catch (Exception var4) {
            var4.printStackTrace();
        }
        return payload;
    }
}