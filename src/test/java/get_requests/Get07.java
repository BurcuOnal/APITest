package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get07 extends HerOkuAppBaseUrl {
       /*
        Given
            https://restful-booker.herokuapp.com/booking/11
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
                        {
                            "firstname": "Jane",
                            "lastname": "Doe",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Extra pillows please"
                        }
     */

    // i)   URL belirlenir
    @Test
    public void get() {

        spec.pathParams("first", "booking"
                , "second", 11
        );


        // ii)  Beklenen data belirlenir
        // iii) Request gönderilir ve Response alınır
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // iv)  Doğrulamalar yapılıır
//1.yol
//        response
//                .then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .body("firstname", equalTo("Jane"))
//                .body("lastname", equalTo("Doe"))
//                .body("totalprice", equalTo(111))
//                .body("depositpaid", equalTo(true))
//                .body("bookingdates.checkin", equalTo("2018-01-01"))
//                .body("bookingdates.checkout", equalTo("2019-01-01"))
//                .body("additionalneeds", equalTo("Extra pillows please"));


//2.yol
        JsonPath json = response.jsonPath();
        assertEquals(200, response.statusCode());
       // assertEquals(ContentType.JSON, response.contentType());
        assertEquals("John", json.getString("firstname"));
        assertEquals("Smith", json.getString("lastname"));
        assertEquals(111, json.getInt("totalprice"));
        assertEquals(true, json.getBoolean("depositpaid"));
        assertEquals("2018-01-01", json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", json.getString("bookingdates.checkout"));
        assertEquals("Breakfast", json.getString("additionalneeds"));

/* 3.yol -- soft Assertion: 3 adımda yapılır
        1-soft assertion objesi oluşturulur
        2-assertionlar bu obje ile yapılır
        3-assertAll ile assertionlar bitirilir
         */




    }
}