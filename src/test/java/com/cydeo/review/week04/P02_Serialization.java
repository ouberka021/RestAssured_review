package com.cydeo.review.week04;

import com.cydeo.Utils.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P02_Serialization extends FakeStoreTestBase {

    static int createdProductID;

    @Test
    @Order(1)
    public void post() {

        Map<String, Object> requestBody = getProduct("POST Product", 1);

        JsonPath jp = RestAssured.given()
                .log().body() // Prints body that you sent to API
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON) // What kind of body we sending to API
                .body(requestBody) // serialization happens in this step
                .when().post("/products")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        // Titles are matching
        String actualTitle = jp.getString("title");
        String expectedTitle = (String) requestBody.get("title");
        Assertions.assertEquals(expectedTitle,actualTitle);

        // Prices are matching
        int actualPrice = jp.getInt("price");
        int expectedPrice = (int) requestBody.get("price");
        Assertions.assertEquals(expectedPrice,actualPrice);


        // print ID value
        createdProductID = jp.getInt("id");
        System.out.println("id = " + createdProductID);
        Assertions.assertNotNull(createdProductID);


    }

    @Test
    @Order(2)
    public void put() {

        Map<String, Object> requestBody = getProduct("PUT Product", 2);

        JsonPath jp = RestAssured.given()
                .log().body()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .pathParam("id", createdProductID)
                .when().put("/products/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        // Titles should be matching
        String actualTitle = jp.getString("title");
        String expectedTitle = (String) requestBody.get("title");
        Assertions.assertEquals(expectedTitle,actualTitle);

    }

    @Test
    @Order(3)
    public void delete() {

        /*
        Response response=RestAssured.get("/products");
        int id = response.path("id[-1]")
         */

        // int idToDelete = RestAssured.get("/products").path("id[-1]");

        Response response = RestAssured.given()
                .log().all()
                .pathParam("id", createdProductID)
                .when().delete("/products/{id}")
                .then()
                .statusCode(200)
                .extract().response();

        String result = response.asString();
        Assertions.assertEquals("true",result);

        Assertions.assertTrue(Boolean.parseBoolean(result));

    }

    public static Map<String,Object> getProduct(String title, int catID){
        Map<String,Object> dataMap=new HashMap<>();

        dataMap.put("title",title);
        dataMap.put("price",10);
        dataMap.put("description","Nicee");
        dataMap.put("categoryId",catID);

        List<String> allImages=new ArrayList<>();
        allImages.add("https://placeimg.com/640/480/any");
        allImages.add("https://placeimg.com/640/480/any");

        dataMap.put("images",allImages);

        return dataMap;

    }

}