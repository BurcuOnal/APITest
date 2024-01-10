package herokuapp_smoke_test;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuRootPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C02GetCreatedBooking extends HerOkuAppBaseUrl {
    /*
    Given        https://restful-booker.herokuapp.com/booking/:id
    When
        Send get request
    Then
        Status code is 200
    And
        Body:
            {
    "firstname": "Veli",
    "lastname": "Can",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Koy Kahvaltısı"
}
     */
    @Test
    public void getCreatedBookingTest() {
        /* oluşturulan bookingin idsini al: exit 0 code ile diğer sınıf bittiği için defult
         değerlere geri döner bu yüzden runner class a ihtiyacımız var.
         (ilk önce create sonra get çalışmalı ki orada oluşan id'yi almalıyım.)
         */
        spec.pathParams("first", "booking", "second", C01CreateBooking.bookingid);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Veli", "Can", 111,
                true, bookingDates, "Koy Kahvaltisi");

        Response response = given(spec).when().get("{first}/{second}");
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
