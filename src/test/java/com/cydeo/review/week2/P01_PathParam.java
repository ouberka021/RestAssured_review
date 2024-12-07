package com.cydeo.review.week2;

import com.cydeo.Utils.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class P01_PathParam extends FakeStoreTestBase {
    private static final Log log = LogFactory.getLog(P01_PathParam.class);
    /**
     * 1- Given accept type is Json
     * 2- Path Parameters value is
     * - id —> 138
     * 3- When user sends GET request to api/v1/products/{id}
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - Print response
     * - id is 138
     * - Name is "My Product"
     * - Category name is "Electronics"
     */
    /**
     * 1- Given accept type is Json
     * 2- Path Parameters value is
     * - id —> 130
     * 3- When user sends GET request to api/v1/products/{id}
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - Print response
     * - id is 130
     * - Name is "My Product"
     * - Category name is "Electronics"
     */
    @Test
    public void response() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .pathParam("id", 130)
                .when().get("/products/{id}");

        //     * - Print response
        response.prettyPrint();


        //     * - Status code should be 200
        Assertions.assertEquals(200, response.statusCode());

        //     * - Content Type is application/json; charset=utf-8
        Assertions.assertEquals("application/json; charset=utf-8", response.contentType());

        //     * - id is 138
        int id = response.path("id");
        System.out.println("id = " + id);
        Assertions.assertEquals(130, id);


        //     * - Name is "My Product"
        Assertions.assertEquals("My Product", response.path("title"));

        //     * - Category name is "Electronics"
        Assertions.assertEquals("Electronics", response.path("category.name"));

        // VERIFY Category link is working ?
        String imageUrl = response.path("category.image");
        System.out.println("imageUrl = " + imageUrl);

        Response responseImage = RestAssured.get(imageUrl);
        Assertions.assertEquals(200, responseImage.statusCode());

        /*
        FindAll
        - a tag --> getAttribute("href")
        - img tag --> getAttribute("src")

        List of link information
        List<String> allLinks=new ArrayList();

        List<String> brokenLinks=new ArrayList();

        Create loop by using allLinks

        int statusCode=RestAssured.get(eachURL).statusCode();
        if(statusCode!=200){
          brokenLinks.add(eachURL);
        }


         */

    }

    @Test
    public void jsonPath() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .pathParam("id", 130)
                .when().get("/products/{id}");

        //     * - Print response
        response.prettyPrint();

        //     * - Status code should be 200
        Assertions.assertEquals(200, response.statusCode());

        //     * - Content Type is application/json; charset=utf-8
        Assertions.assertEquals("application/json; charset=utf-8", response.contentType());

        // CREATE JSONPATH OBJECT
        JsonPath jp = response.jsonPath();

        /*
        Jsonpath object does not have any data about
        - StatusCode
        - Headers
        - Content Type
         */


        //     * - id is 138
        int id = jp.getInt("id");
        System.out.println("id = " + id);
        Assertions.assertEquals(130, id);


        //     * - Name is "My Product"
        Assertions.assertEquals("My Product", jp.getString("title"));

        //     * - Category name is "Electronics"
        Assertions.assertEquals("Electronics", jp.getString("category.name"));


    }

    @Test
    public void hamcrest() {
        JsonPath jsonPath =
                RestAssured.given().accept(ContentType.JSON)
                        .pathParam("id", "130")
                        .when().get("/products/{id}")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON.withCharset("utf-8"))
                        .body("id", Matchers.is(130))
                        .body("title", Matchers.is("My Product"))
                        .extract().jsonPath();

        int catID = jsonPath.getInt("category.id");
        System.out.println(catID);


    }

    @Test
    public void allInOneCategories() {

        Response response = getResponse(130, "/products/{id}");

        // Content Type
        Assertions.assertEquals("application/json; charset=utf-8", response.contentType());

        // Create JSON PATH

    }

    @Test
    public void allInOneUsers() {

        Response response = getResponse(1, "/users/{id}");

        // Content Type
        Assertions.assertEquals("application/json; charset=utf-8", response.contentType());

    }

    /*
    /api/v1/categories/{id}
    /api/v1/users/{id}
    /api/v1/products/{id}

     */
    public static Response getResponse(int paramValue, String endpoint) {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .pathParam("id", paramValue)
                .when().get(endpoint);

        Assertions.assertEquals(200, response.statusCode());

        return response;
    }

}
