package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {

 /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
       And
           Among the data there should be someone whose firstname is "John" and lastname is "Smith"
    */
    @Test
    public void get (){

        spec.pathParams("first", "booking")
                .queryParams("firstname","John","lastname","Smith");

       Response response =  given(spec).when().get("{first}");
       response.prettyPrint();

       //1.yol (listedeki bookinid içeriyor mu içermiyor mu)

        response
                .then()
                .statusCode(200)
                .body("bookingid",hasSize(greaterThan(0))) //1.1 yol
                .body(containsString("bookingid"));//1.2 yol


       //2.yol
       Assert.assertEquals(200, response.statusCode());
       assertTrue(response.asString().contains("bookingid"));



    }




}
