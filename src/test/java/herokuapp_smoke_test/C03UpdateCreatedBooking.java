package herokuapp_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuRootPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C03UpdateCreatedBooking extends HerOkuAppBaseUrl {
    /*
    Given        https://restful-booker.herokuapp.com/booking/:id

    And
     {
    "firstname" : "Nazar",
    "lastname" : "Can",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "kahve"
}
    When
        Send put request

    Then
        Status code is 200

    And
        Body:
{
    "firstname": "Nazar",
    "lastname": "Can",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "kahve"
}
     */
    @Test
    public void updateCreatedBookingTest() {
        spec.pathParams("first", "booking",
                "second", C01CreateBooking.bookingid);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Nazar", "Can", 111,
                true, bookingDates, "kahve");

        Response response = given(spec).body(expectedData).when().put("{first}/{second}");
        response.prettyPrint();
        // dönen response bu şekilde:
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

