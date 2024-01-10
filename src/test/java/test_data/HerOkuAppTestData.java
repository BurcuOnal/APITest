package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

/*
 Map<String, String> bookingMap = new HashMap<>();
        bookingMap.put("checkin", "2018-01-01");
        bookingMap.put("checkout", "2019-01-01");
 */
    public static Map<String, String> bookingMapper (String checking, String checkout){
        Map<String, String> map = new HashMap<>();
        map.put("checkin", checking);
        map.put("checkout", checkout);
        return map;
    }

    /*
      Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Jane");
        expectedData.put("lastname", "Doe");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingMap);
        expectedData.put("additionalneeds", "Extra pillows please");
     */
    public static Map<String, Object> herOkuAppMapper(String firstname, String lastname,
                                                      Integer totalprice, Boolean depositpaid,
                                                      Map<String, String> bookingDates,
                                                      String additionalneeds ){

        Map<String, Object> map = new HashMap<>();
       if (firstname !=null){
           map.put("firstname", firstname);
       }
       if(lastname !=null) {
           map.put("lastname", lastname);
       }
        if (totalprice !=null){
            map.put("totalprice", totalprice);
        }
        if(depositpaid !=null) {
            map.put("depositpaid", depositpaid);
        }
        if(bookingDates !=null) {
            map.put("bookingdates", bookingDates);
        }

         if (additionalneeds !=null ) {

             map.put("additionalneeds", additionalneeds);
         }
        return map;

    }


}


