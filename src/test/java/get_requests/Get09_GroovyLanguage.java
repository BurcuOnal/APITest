package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get09_GroovyLanguage extends JsonPlaceHolderBaseUrl {
       /*
       Given
             https://jsonplaceholder.typicode.com/todos
      When
           I send GET Request to the URL
      Then
           1)Status code is 200
           2)Print all ids greater than 190 on the console
             Assert that there are 10 ids greater than 190
           3)Print all userIds whose ids are less than 5 on the console
             Assert that the number of userIds whose ids are less than 5 is 4
           4)Print all titles whose ids are less than 5
             Assert that "delectus aut autem" is one of the titles whose id is less than 5
   */


    @Test
    public void get (){
        spec.pathParams("first","todos");

        Response response= given(spec).when().get("{first}");

        JsonPath json = response.jsonPath();
        response
                .then()
                .statusCode(200);

        //--------------2)Print all ids greater than 190 on the console
        //1.yol: Loop Kullanarak
//        List<Integer> idsGreaterThan190 = new ArrayList<>();
//
//        List <Integer> idList = json.getList("id"); //bütün idlerin listi döndürür
//
//
//        for (Integer w: idList){
//            if (w>190){
//                idsGreaterThan190.add(w);
//            }
//
//        }
//        System.out.println(idsGreaterThan190);

        //2.yol: Groovy Lang: koleksiyonlarda şartlı sorgular varsa

        List <Integer> idGreater =  json.getList("findAll{it.id > 190}.id");// idsi 190 dan büyük olan bütün itemler(it)ın idleri
        // - süslü parantezden sonra ne blmak istiyorsak onu yazarak farklı şeyler getirebiliriz
        System.out.println(idGreater);
        // System.out.println(json.getList("findAll{it.title == 'bıdıbıdıd'}.name")); // title'ı bıdıbıdı olan bütün itemler(it)ın nameleri

    //--------- Assert that there are 10 ids greater than 190
        assertEquals(10,idGreater.size());

        // -----------3)Print all userIds whose ids are less than 5 on the console

        List <Integer> idslessThanFive = json.getList("findAll { it.id < 5 }.userId");
        System.out.println(idslessThanFive);

        // -------Assert that the number of userIds whose ids are less than 5 is 4

        assertEquals(4,idslessThanFive.size());
        // ---    4)Print all titles whose ids are less than 5

        List <Integer> titleLessThanFive = json.getList("findAll { it.id < 5 }.title");
        System.out.println(titleLessThanFive);

        // ------ Assert that "delectus aut autem" is one of the titles whose id is less than

        assertTrue(titleLessThanFive.contains("delectus aut autem"));
    }
}
