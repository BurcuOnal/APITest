package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import put_requests.Put01;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static test_data.HerOkuAppTestData.bookingMapper;
import static test_data.HerOkuAppTestData.herOkuAppMapper;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Get11_NestedMap extends HerOkuAppBaseUrl {

    /*
        Given            https://restful-booker.herokuapp.com/booking/452
        When
            I send GET Request to the url
        Then
            Response body should be like that;
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

    @Test
    public void get() {
        // -------------- 1-url kurulur
        spec.pathParams("first", "booking", "second", 19);

        //-------------- 2-beklenen data kurulur

       /*
        nested yapılarda beklenen data en içteki yapıdan başlayarak oluşturulur

          "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    }
         */
        Map<String, String> bookingMap = new HashMap<>();
        bookingMap.put("checkin", "2018-01-01");
        bookingMap.put("checkout", "2019-01-01");


        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Jane");
        expectedData.put("lastname", "Doe");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingMap);
        expectedData.put("additionalneeds", "Extra pillows please");


        //------ 3- request gönderip response alınır
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();


        //-------- 4- dogrulamalar yapılır

        //Map'li yöntem:
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin")); //typeCasting yapılacak
        assertEquals(bookingMap.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));

        // Body'li yöntem:

        response
                .then()
                .statusCode(200)
                .body("firstname", equalTo(expectedData.get("firstname")))
                .body("bookingdates.checkin", equalTo(bookingMap.get("checkin"))); // blablaablaa aynı şekilde devam eder


        // JsonPathli yöntem
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("firstname"), json.getString("firstname"));
        assertEquals(bookingMap.get("checkin"), json.getString("bookingdates.checkin"));


    }

    @Test
    public void get11b (){
        // -------------- 1-url kurulur
        spec.pathParams("first", "booking", "second", 19);

        //-------------- 2-beklenen data kurulur

       /*
        nested yapılarda beklenen data en içteki yapıdan başlayarak oluşturulur

          "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    }
         */
        Map<String, String> bookingMap = bookingMapper("2018-01-01", "2019-01-01");

        /*
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

        Map<String, Object> expectedData = herOkuAppMapper("Jane", "Doe",
                111,true, bookingMap,"Extra pillows please");



        //------ 3- request gönderip response alınır
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();


        //-------- 4- dogrulamalar yapılır

        //Map'li yöntem:
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin")); //typeCasting yapılacak
        assertEquals(bookingMap.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));

        // Body'li yöntem:

        response
                .then()
                .statusCode(200)
                .body("firstname", equalTo(expectedData.get("firstname")))
                .body("bookingdates.checkin", equalTo(bookingMap.get("checkin"))); // blablaablaa aynı şekilde devam eder


        // JsonPathli yöntem
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("firstname"), json.getString("firstname"));
        assertEquals(bookingMap.get("checkin"), json.getString("bookingdates.checkin"));

    }

}
