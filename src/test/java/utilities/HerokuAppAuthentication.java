package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HerokuAppAuthentication {

    /*
    //token geldi mi gelmedi mi test etmek için
    public static void main(String[] args) {
        System.out.println( "token:" +
                generateToken()); }
     */


    public static String generateToken (){
        String credential = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
       Response response = given().
               body(credential).
               contentType(ContentType.JSON).
               when().post("https://restful-booker.herokuapp.com/auth");

       return response.jsonPath().getString("token");

    }
}
