package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Put01 extends JsonPlaceHolderBaseUrl {

    /*
     Given  1) https://jsonplaceholder.typicode.com/todos/198
           2) {
                "userId": 55,
                "title": "Wash the dishes",
                "completed": false
              }
     When
            I send PUT Request to the Url
     Then
             Status code is 200
             And response body is like   {
                                       "userId": 55,
                                       "title": "Wash the dishes",
                                       "completed": false
                                      }
    */
    @Test
    public void put() {
        //1-url hazırla
        spec.pathParams("first", "todos", "second", 198);
        //2-beklenen datayı oluştır. (payload=body demek)
        Map<String, Object> payLoad = jsonPlaceHolderMapper(55, "Wash the dishes", false);
        //3-request gönderip response alınır
        Response response = given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        //4-doğrulamalar yapılır

        Map<String, Object> actualData = response.as(HashMap.class); // deserialization: json objesini mapa dönüştürdük

        assertEquals(200, response.statusCode());
        assertEquals(payLoad.get("userId"), actualData.get("userId"));
        assertEquals(payLoad.get("title"), actualData.get("title"));
        assertEquals(payLoad.get("completed"), actualData.get("completed"));


    }
}
