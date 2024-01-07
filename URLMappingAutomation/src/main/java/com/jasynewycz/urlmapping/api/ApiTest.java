package com.jasynewycz.urlmapping.api;

import io.restassured.RestAssured;
import org.hamcrest.core.IsEqual;


/**
 * End to end test of the REST end point.
 * Expects by default to have the service running at:
 * http://127.0.0.1:8081
 * If not then this needs passing in as the first argument. The code will append:
 * /urlmap/api/v1
 * TODO - Note this is incomplete, many more test cases should be added here.
 */
public class ApiTest {

    public static final String DEFAULT_BASE_URL = "http://127.0.0.1:8081/urlmap/api/v1";

    public static void main(String[] args) {

        RestAssured.baseURI = DEFAULT_BASE_URL;

        if(args.length > 0) {
            RestAssured.baseURI = args[0] + "/urlmap/api/v1";
        }

        String testUrl1 = "https://www.bbc.co.uk/news/live/world-europe-67439603";

        RestAssured.given().body(testUrl1).header("Content-Type","text/plain")
                .when().post("mapping").then().log().all().assertThat().statusCode(201)
                .body("longUrl", IsEqual.equalTo(testUrl1));
    }
}
