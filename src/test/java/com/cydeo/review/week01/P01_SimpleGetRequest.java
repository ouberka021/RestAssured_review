package com.cydeo.review.week01;

import com.cydeo.Utils.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_SimpleGetRequest extends TestBase {

    /**
     * 1. Send request to HR ORDS url and save the response
     * 2. GET /regions
     * 3. Store the response in Response Object that comes from GET Request
     * 4. Print out followings
     *     - Headers
     *     - Content-Type
     *     - Status Code
     *     - Response
     *     - Date
     *     - Verify response body has "Europe"
     *     - Verify response has Date
     */



    @Test
    public void test1() {

      Response response =  given()
              .log().uri() // print uri info into console
                .accept(ContentType.JSON)
                .when()
                .get("/regions");

      //  Content-Type
        // response.getStatusCode() is also same
        int statusCode = response.getStatusCode();
        System.out.println("Actual Status code : "+statusCode);
        assertEquals(200,statusCode);
    // Headers
        System.out.println("------------ Headers --------");
        System.out.println(response.headers());
        System.out.println(response.getHeaders());
        //  Content-Type
        String contentType = response.header("Content-Type");
        System.out.println("Actual Content Type : "+response.contentType());
        System.out.println("Actual Content Type : "+contentType);
        assertEquals("application/json", contentType);

//                *     - Response
        System.out.println("------- Response -----");
      response.prettyPrint();
//                *     - Date
        System.out.println("response.header(\"Date\") = "+response.header("Date"));
//                *     - Verify response body has "Europe"
       // response.body().asString();
        boolean isEurope = response.asString().contains("Europe");
        Assertions.assertTrue(isEurope);
        System.out.println(isEurope);

//                *     - Verify response has Date
        boolean hasDate = response.headers().hasHeaderWithName("Date");
        System.out.println(hasDate);
        Assertions.assertTrue(hasDate);


    }

}
