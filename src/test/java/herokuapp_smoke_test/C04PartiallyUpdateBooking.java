package herokuapp_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C04PartiallyUpdateBooking  extends HerOkuAppBaseUrl {
    /*
   Given        https://restful-booker.herokuapp.com/booking/:id
   And
       {
   "firstname" : "Naz",
   "lastname" : "Canan",
   "additionalneeds" : "Çay"
}
   When
       Send  patch request
   Then
       Status code is 200
   And
      Body:
{
   "firstname": "Naz",
   "lastname": "Canan",
   "totalprice": 111,
   "depositpaid": true,
   "bookingdates": {
       "checkin": "2018-01-01",
       "checkout": "2019-01-01"
   },
   "additionalneeds": "Çay"
}
    */
    @Test
    public void partiallyUpdateBookingTest() {
        spec.pathParams("first", "booking",
                "second", C01CreateBooking.bookingid);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Naz", "Canan", 111,
                true, bookingDates, "çay");


        Map<String, Object> payLoad = HerOkuAppTestData.herOkuAppMapper("Naz", "Canan",
                null, null, null, "çay");


        Response response = given(spec).body(payLoad).when().patch("{first}/{second}");
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
