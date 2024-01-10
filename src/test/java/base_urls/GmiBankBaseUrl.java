package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.GmiBankAuthentication;

import static utilities.HerokuAppAuthentication.generateToken;

public class GmiBankBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setUp(){
        String baseUrl = "https://gmibank.com";
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer " + GmiBankAuthentication.generateToken())
                .setBaseUri(baseUrl)
                .build();
    }
}
