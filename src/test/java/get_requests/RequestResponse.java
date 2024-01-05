package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResponse {

    /*
    1) Sistem içim manual testleri postman kullanarak yaparız
    2) Otomasyon testi için RestAssured library kullanacagım
    3) Test case yazılırken Gherkin dili kullanılır;
        Given: ön koşullar --url
        When: aksiyon bildirir -- get, post.. method isimleri
        Then: Doğrulamalar
        And: Tekrarlı koşulları bağlamakta kullanılır

   4) Test otomasyon adımları:
         1-Url belirlenir
         2-Beklenen data belirlenir
         3-Request gönderip response alınır
         4-Doğrulamalar yapılır

     */
    public static void main(String[] args) {
        // 1-Url belirlenir

        String url = "https://petstore.swagger.io/v2/pet/1";

        // 2-Beklenen data belirlenir


        //3-Request gönderip response alınır
       Response response = given().when().get(url);
       response.prettyPrint(); //string döndürür ve consorla yazdırır
            // status code nasıl ulaşılır

        int statusCode = response.statusCode();
        System.out.println("status code: " + statusCode);
        System.out.println(response.statusCode()); // consola yazdırma aynısı yukardakinin
        System.out.println("---------------------------");

            //status Line nasıl ulaşılır

        System.out.println(response.statusLine());
        System.out.println("-----------");
            // Content Type nasıl ulaşılır

        System.out.println(response.contentType());
        System.out.println("---------------");

            // Headerlardan herhangi birine nasıl ulaşılır
        System.out.println(response.header("Server"));
        System.out.println(response.header("Date"));
        System.out.println("----------------");

            //bütün headerlara nasıl ulaşılır
        System.out.println("-----Bütün HEADERlar-------");
        System.out.println(response.headers());
        System.out.println("--------------");

            //response süresine nasıl ulaşılır
        System.out.println(response.time());
        System.out.println("---------------");

    }
}
