package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post02 extends JsonPlaceHolderBaseUrl {

    //post01 deki  map oluşturmayı methodlaştırma

    @Test
    public void postMap() {
        //1-url hazırla
        spec.pathParams("first", "todos");


        //2-beklenen datayı oluştır. (payload=body demek)
        Map<String, Object> payLoad = jsonPlaceHolderMapper(55, "Tidy your room", false);


        //3-request gönderip response alınır
        Response response = given(spec).body(payLoad).when().post("{first}");
        //Serialization: java objesinin Json objesine dönüştürmek bunun için pom'a Jackson Databind yüklendi.


        //4-doğrulamalar yapılır
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(201, response.statusCode());
        assertEquals(payLoad.get("userId"), actualData.get("userId"));
        assertEquals(payLoad.get("title"), actualData.get("title"));
        assertEquals(payLoad.get("completed"), actualData.get("completed"));

    }

}
