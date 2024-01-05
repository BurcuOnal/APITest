package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class Get2 {
/*
       Given           https://restful-booker.herokuapp.com/booking/0
       When
           User send a GET Request to the url
       Then
           HTTP Status code should be 404
       And
           Status Line should be HTTP/1.1 404 Not Found
       And
           Response body contains "Not Found"
       And
           Response body does not contain "TechProEd"
       And
           Server is "Cowboy"
 */
    @Test
    public void get (){
        String url = "https://restful-booker.herokuapp.com/booking/0";
        Response response = given().when().get(url);
        response.prettyPrint();
        response.
                then().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found").
                body(containsString("Not Found")).
                body(not(containsString("TechProEd")));


        String respnseST= response.asString();
        assertTrue(respnseST.contains("Not Found")); // body(containsString("Not Found")).
        assertFalse(respnseST.contains("TechProEd")); //body(not(containsString("TechProEd")));

        String server = response.header("Server");
        assertEquals(server,"Cowboy");

    }
}
