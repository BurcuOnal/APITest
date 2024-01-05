package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get05 extends JsonPlaceHolderBaseUrl {
    /*
       Given
        https://jsonplaceholder.typicode.com/todos
       And
           Accept type is "application/json" ---- before da
       When
            I send a GET request to the Url
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           There should be 200 todos
       And
           "quis eius est sint explicabo" should be one of the todos title
       And
           2, 7, and 9 should be among the userIds
    */
    @Test
    public void get() {
        spec.pathParams("first", "todos");

        Response response = given(spec).when().get("{first}"); //given(spec) yerine .spec(spec) eklenebilir
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // tek bir body içerisinde yazdığımız için soft assertion oluyor
                // .body("[0].title", equalTo("blablabla")); //-- body listedeki ilk eleman
                .body("title", hasSize(200)                                     // hasSize() list dönen methodun boyutunu döndürür,
                        , "title", hasItem("quis eius est sint explicabo") // hasItem() listede verilen değerlerin içerip içermediğini
                        , "userId", hasItems(2, 7, 9))




        ;

    }
}
