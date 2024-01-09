package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12_NestedPojo extends HerOkuAppBaseUrl {

/*
        Given
         https://restful-booker.herokuapp.com/booking/1283
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like:
 		              {
                        "firstname": "John",
                        "lastname": "Smith",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                           bookinDatesPojo - bookingDates
                        },
                        "additionalneeds": "Breakfast"
                    }
     */

    @Test
    public void get (){

        spec.pathParams("first","booking", "second",1927);


        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2018-01-01");

        BookingPojo expectedData = new BookingPojo( "John",
                "Smith",  111, true,
                bookingDates, "Breakfast");


       Response response=  given(spec).when().get("{first}/{second}");

       BookingPojo actualData = response.as(BookingPojo.class);

       assertEquals(200, response.statusCode());
       assertEquals(expectedData.getFirstname(),
               actualData.getFirstname());
       assertEquals(expectedData.getLastname(),
               actualData.getLastname());
       assertEquals(expectedData.getTotalprice(),
               actualData.getTotalprice());
       assertEquals(expectedData.getDepositpaid(),
               actualData.getDepositpaid());
       assertEquals(expectedData.getAdditionalneeds(),
               actualData.getAdditionalneeds());
       assertEquals(bookingDates.getCheckin(),
               actualData.getBookingdates().getCheckin()); // aşağıdaki ve bu aynı
       assertEquals(expectedData.getBookingdates().getCheckout(),
               actualData.getBookingdates().getCheckout());

    }
}
