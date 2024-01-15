import org.testng.annotations.Test;
import request.addProductBody.Body;
import response.AddProduct.ResponseBody;
import response.getAllProduct.ProductResponse;
import restAssuredComman.ApiUtils;
import restAssuredComman.ApiWrapper;

import java.util.HashMap;
import java.util.Objects;

public class ApiTestCases {
    // Using Object mapper
    @Test(enabled = true)
    public void validateProductData(){
        ApiWrapper objApi = new ApiWrapper();
//        ApiUtils objUtils = new ApiUtils();
        ProductResponse objTodoResponse = ApiUtils.jsonStringToObject(objApi.callGetApi("https://dummyjson.com/products/"), ProductResponse.class);
        assert (objTodoResponse.getLimit() == objTodoResponse.getProducts().size() );
    }
    @Test(enabled = true)
    public void validateAddProduct(){
        ApiWrapper objApi = new ApiWrapper();
        String url = "https://dummyjson.com/products/add";
        // Set headers
        HashMap<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        //Set body
//        Object mapper method used in api chaining
        Body objBody = new Body();
        objBody.setTitle("Logan product");
        objBody.setBrand("Ps");
        String resBody = ApiUtils.objectToJsonString(objBody);
//        JSON String method so simple task validation
//        String resBody = "{\"title\":\"BMW Pencil\"}";
        ResponseBody objResponseBodyResponse = ApiUtils.jsonStringToObject(objApi.callPostApi(url,resBody,header), ResponseBody.class);
        assert (Objects.equals(objResponseBodyResponse.getTitle(), objBody.getTitle()));
//        assert (Objects.equals(objResponseBodyResponse.getTitle(),"BMW Pencil"));
    }
}
