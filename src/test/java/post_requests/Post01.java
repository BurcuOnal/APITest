package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {

    /*
       Given
        1) https://jsonplaceholder.typicode.com/todos
        2)  {
               "userId": 55,
               "title": "Tidy your room",
               "completed": false
            }
      When
          I send POST Request to the Url

      Then
          Status code is 201
      And
          response body is like {
                                  "userId": 55,
                                  "title": "Tidy your room",
                                  "completed": false,
                                  "id": 201
                                  }
   */
//1.yol
    @Test
    public void post() {
        //1-url hazırla
        spec.pathParams("first", "todos");
        //2-beklenen datayı oluştır. (payload=body demek)
        String payLoad = "{\n" +
                "               \"userId\": 55,\n" +
                "               \"title\": \"Tidy your room\",\n" +
                "               \"completed\": false\n" +
                "            }";

        //3-request gönderip response alınır

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();
        //4-doğrulamalar yapılır
        JsonPath json = response.jsonPath();
        assertEquals(201, response.statusCode());
        assertEquals(5, json.getInt("userId"));
        assertEquals("Tidy your room", json.getString("title"));
        assertEquals(false, json.getBoolean("completed"));


    }

    // 2.yol:
    @Test
    public void postMap() {
        //1-url hazırla
        spec.pathParams("first", "todos");
        //2-beklenen datayı oluştır. (payload=body demek)
        Map<String, Object> payLoad = new HashMap<>();
        payLoad.put("userId", 55);
        payLoad.put("title", "Tidy your room");
        payLoad.put("completed", false);
        System.out.println(payLoad);

        //3-request gönderip response alınır

        Response response = given(spec).body(payLoad).when().post("{first}");
            //Serialization: java objesinin Json objesine dönüştürmek bunun için pom'a Jackson Databind yüklendi.

        //4-doğrulamalar yapılır

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(201, response.statusCode());
        assertEquals(payLoad.get("userId"), actualData.get("userId"));
        assertEquals(payLoad.get("title"), actualData.get("title"));
        assertEquals(payLoad.get("completed"), actualData.get("completed"));


    }
}
