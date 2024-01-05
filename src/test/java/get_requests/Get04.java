package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Get04 extends JsonPlaceHolderBaseUrl {

    /*
        Given
          https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be "application/json"
		And
		    "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
		And
		    "completed" is false
		And
		    "userId" is 2
 */

    @Test
    public void get() {

        spec.pathParams("first", "todos",
                "second", 23);

        Response response = given(spec).when().get("{first}/{second}");
//given içinde bütün ön koşullar spec ile -- first second olarak url devamı için isim verdik, onları get'de tanıttık
        response.prettyPrint();

// hard assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) //application/json yazmak yerine bunu kullanabiliriz
                .body("title", Matchers.equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false)) //Matchers classindan equalto çağırabiliriz yukardaki gibi ya da böyle aynısı
                .body("userId", is(2)); // equalTo ve is aynı şey

// soft assertion: bir assertion kalsa bile diğerleri geçebilir -- ikisi aynı şey

        response
                .then()
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")
                        , "completed", equalTo(false)
                        , "userId", equalTo(2)
                );

    }

}
