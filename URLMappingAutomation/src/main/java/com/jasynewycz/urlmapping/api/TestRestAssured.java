package com.jasynewycz.urlmapping.api;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import org.hamcrest.core.IsEqual;

public class TestRestAssured {

    public static final String DEFAULT_BASE_URL = "http://127.0.0.1:8081/urlmap/api/v1";

    public static void main(String[] args) {

        RestAssured.baseURI = DEFAULT_BASE_URL;

        if(args.length > 0) {
            RestAssured.baseURI = args[0];
        }

        String testUrl1 = "https://www.bbc.co.uk/news/live/world-europe-67439603";

        RestAssured.given().body(testUrl1).header("Content-Type","text/plain")
                .when().post("mapping").then().log().all().assertThat().statusCode(201)
                .body("longUrl", IsEqual.equalTo(testUrl1));
    }
}
