package com.cydeo.review.week04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

public class P03_ApiKey {
    @Test
    public void apiKey() {
       // String apiKey = "81nW-b5ob7f4Z3OxtQQQsQdHGVb-xTQXgV4kBDy_kiBiBViC";
        String apiKey =  System.getenv("APIKEY");
        System.out.println("API Key: " + apiKey);
        JsonPath jp = RestAssured.given()
                .accept(ContentType.JSON)
                .baseUri("https://api.currentsapi.services/v1")
                .header("Authorization", apiKey)
                .when().get("/latest-news")
                .then()
                .statusCode(200)
                .extract().jsonPath();
        // Create POJO
// Deserialization
/*
How to store sensitive data in your framework ?
- In my framework I store all sensitive data (credentials, apiKey etc)
in Environmental Variables also I create same Environmental Variables in
REMOTE machine ( Jenkins ). With the help this I dont share any sensitive data
 */


    }
}
