package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03 extends HerOkuAppBaseUrl {
     /*
Given 1) https://restful-booker.herokuapp.com/booking2) {
    "firstname": "John",
    "lastname": "Doe",
    "totalprice": 11111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-09-09",
        "checkout": "2021-09-21"
     }
  }
When
I send POST Request to the Url
Then
Status code is 200
And response body should be like {
                                   "bookingid": 5315,
                                   "booking": {
                                       "firstname": "John",
                                       "lastname": "Doe",
                                       "totalprice": 11111,
                                       "depositpaid": true,
                                       "bookingdates": {
                                           "checkin": "2021-09-09",
                                           "checkout": "2021-09-21"
                                       }
                                   }
                                }
*/

    @Test
    public void post() {
        //1-Url kuruldu:
        spec.pathParams("first", "booking");

        //2- Beklenen data kuruldu:
        /*
        "John","Doe", 11111, true,
        "bookingdates": {     --> bookingMap--> burası artık bu oldu
        "checkin": "2021-09-09",
        "checkout": "2021-09-21"
         */

        Map<String, String> bookingMap = HerOkuAppTestData.bookingMapper("2021-09-09",
                "2021-09-21");
        Map<String, Object> payLoad = HerOkuAppTestData.herOkuAppMapper("John", "Doe",
                11111, true, bookingMap
                , null);


        //3- request --> response

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        //4-Doğrulamalar yapıldı:


        JsonPath json= response.jsonPath();

        Map<String, Object> actualData = response.as(HashMap.class);

        //1.
        assertEquals(payLoad.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        //2.
        assertEquals(payLoad.get("firstname"), json.getString("booking.firstname"));
        assertEquals(bookingMap.get("checkin"), json.getString("booking.bookingdates.checkin"));




    }
}
