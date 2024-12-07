package com.cydeo.review.week2;

import com.cydeo.Utils.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P02_QueryParam extends FakeStoreTestBase {


    /**
     * 1- Given accept type is Json
     * 2- Query Parameters value is
     * - limit —> 10
     * - offset —> 0
     * 3- When user sends GET request to /products
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - Each product has id
     * - Each product has category id
     * - Get all product names
     * - Get product ids
     * - Get all category names
     */
    @Test
    public void queryParam() {

        //queryParams
        Map<String, Integer> params = new HashMap<>();
        params.put("limit",10);
        params.put("offset", 0);
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .queryParams(params)
//                .and().queryParam("limit", 10)
//                .and().queryParam("offset", 0)
                .when()
                .get("/products");
        response.prettyPrint();
        JsonPath js = response.jsonPath();
        //     * - Verify status code is 200
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json; charset=utf-8", response.contentType());

      //  * - Each product has id
        List<Integer> allIDs = js.get("id");
        for (Integer id : allIDs){
            Assertions.assertNotNull(id);

        }
       // Assertions.assertTrue(js.get("id").toString().contains("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"));
      //  * - Each product has category id
        List<Integer> allCatIDs = js.getList("category.id");
        for (Integer id : allCatIDs){
            Assertions.assertNotNull(id);

        }
      //  * - Get all product title
        List<Integer> allTitles = js.getList("title");
        System.out.println("allTitles = " + allTitles);
        System.out.println("allTitles.size() = " + allTitles.size());
      //  * - Get all category names
        List<String> allCatNames = js.getList(  "category.name") ;
        System.out.println("allCatNames = " + allCatNames) ;
        System.out.println("allCatNames.size() = " + allCatNames.size());
    }

    public static Map<String, Object> getBooks(){
        Map<String, Object> params = new HashMap<>();
        params.put("limit",10);
        params.put("offset", 0);
        return params;
    }

}
