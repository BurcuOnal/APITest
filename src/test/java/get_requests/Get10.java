package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Get10 extends JsonPlaceHolderBaseUrl {
    /*
    Given
    https://jsonplaceholder.typicode.com/todos/2
    When
        I send GET Request to the URL
    Then
        Status code is 200
    And
        "completed" is false
    And
        "userId" is 1 -- id zaten sürekli değişen bir şey olduğu için aasert edilmez
    And     "title" is "quis ut nam facilis et officia qui"
    And     header "Via" is "1.1 vegur"
    And     header "Server" is "cloudflare"
    And     body is like
        {
        "userId": 1,
        "id": 2,
        "title": "quis ut nam facilis et officia qui",
        "completed": false
        }
 */
    @Test
    public void get() {

        //1-url kurulur
        spec.pathParams("first", "todos", "second", 2);
        //2-beklenen data kurulur
        Map<String, Object> expectedData = jsonPlaceHolderMapper(1,"quis ut nam facilis et officia qui", false);
        expectedData.put("Via","1.1 vegur" );
        expectedData.put("Server","cloudflare");

        // 3- request gönderip response alınır
       Response response=  given(spec).when().get("{first}/{second}");

       // 4- dogrulamalar yapılır

        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

        assertEquals(expectedData.get("Via"),response.header("Via"));
        assertEquals(expectedData.get("Server"),response.header("Server"));



    }
}
