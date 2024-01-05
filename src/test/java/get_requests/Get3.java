package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Get3 {
    /*
        Given            https://jsonplaceholder.typicode.com/todos/23
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
    public void get (){

    String url = "https://jsonplaceholder.typicode.com/todos/23";
    Response response = given().when().get(url);
    response.prettyPrint();

// hard assertion
    response
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON) //application/json yazmak yerine bunu kullanabiliriz
            .body("title", Matchers.equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
            .body("completed", equalTo(false)) //Matchers classindan equalto çağırabiliriz yukardaki gibi ya da böyle aynısı
            .body("userId", is(2)); // equalTo ve is aynı şey

// soft assertion: bir assertion kalsa bile diğerleri geçebilir

    response
            .then()
            .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")
                    , "completed", equalTo(false)
                    , "userId", equalTo(2)
            );

}

}
