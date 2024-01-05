package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends PetStoreBaseUrl {

     /*
    Given        https://petstore.swagger.io/v2/pet/235
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
           {
            "id": 235,
            "category": {
                "id": 0,
                "name": "Aldo"
            },
            "name": "Dog",
            "photoUrls": [
                "www.ornekolacakyadim1.com",
                "www.ornekolacakyadim2.com"
            ],
            "tags": [
                {
                    "id": 0,
                    "name": "Seven"
                },
                {
                    "id": 0,
                    "name": "Eight"
                }
            ],
            "status": "pending"
           }

     */

    @Test
    public void get(){

        spec.pathParams("first","pet", "second", 235);
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();

        assertEquals("Aldo",json.getString("category.name"));
        assertEquals("Dog",json.getString("name"));
        assertEquals("www.ornekolacakyadim1.com",json.getString("photoUrls[0]"));
        assertEquals("www.ornekolacakyadim2.com",json.getString("photoUrls[1]"));
        assertEquals("Seven",json.getString("tags[0].name"));
        assertEquals("Eight",json.getString("tags[1].name"));




    }


}
