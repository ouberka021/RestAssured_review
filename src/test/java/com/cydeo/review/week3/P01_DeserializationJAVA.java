package com.cydeo.review.week3;

import com.cydeo.Utils.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P01_DeserializationJAVA extends FakeStoreTestBase {
    /**
     * Send request to FakeStoreTestBase url and save the response
     * Accept application/json
     * Path Param id is 1
     * Query Param limit 10
     * Query Param offset 0
     * GET /categories/{id}/products
     * Store the response in Response Object that comes from get Request
     * Print out followings
     * - Print response
     * - Content-Type is application/json; charset=utf-8
     * - Status Code is 200
     * - Retrieve data as JAVA Collections and print out following information
     * <p>
     * System.out.println("====== GET FIRST PRODUCTS ======");
     * System.out.println("====== GET FIRST IMAGES ======");
     * System.out.println("====== GET FIRST PRODUCT CATEGORY ======");
     * System.out.println("====== GET ALL PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
     */

    @Test
    public void task1() {
        JsonPath jsonPath =
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .pathParam("id", 1)
                        .queryParam("limit", 10)
                        .queryParam("offset", 0)
                        .when().get("/categories/{id}/products")
                        .then()
                        .statusCode(200)
                        .contentType("application/json")
                        .extract().jsonPath();

        //     System.out.println("====== GET FIRST PRODUCTS ======");
        Map<String, Object> firstProduct = jsonPath.getMap("[0]");
        System.out.println("firstProduct = " + firstProduct);

        //     System.out.println("====== GET FIRST IMAGES ======");

        List<String> images = (List<String>) firstProduct.get("images");

        System.out.println("images = " + images);

        //     System.out.println("====== GET FIRST PRODUCT CATEGORY ======");
        Map<String, Object> category = (Map<String, Object>) firstProduct.get("category");
        System.out.println("category.get(\"name\") = " + category.get("name"));
        //  System.out.println("====== GET ALL PRODUCTS ======");
        List<Map<String, Object>> allProducts = jsonPath.getList("");
        System.out.println("allProducts.size() = " + allProducts);

        //     System.out.println("====== GET SECOND PRODUCTS ======");
        Map<String, Object> secondProduct = allProducts.get(1);
        System.out.println("secondProduct = " + secondProduct);

        //     System.out.println("====== GET SECOND PRODUCTS PRICE ======");
        int price = (int) secondProduct.get("price");
        System.out.println("price = " + price);

        //     System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
        List<String> secondProductImages = (List<String>) secondProduct.get("images");
        System.out.println("secondProductImages = " + secondProductImages);
        //  System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
        String firstImage = secondProductImages.get(0);
        System.out.println("firstImage = " + firstImage);

        //     System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
        Map<String, Object> secondProductCategory = (Map<String, Object>) secondProduct.get("category");
        System.out.println("secondProductCategory = " + secondProductCategory);

        //     System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
        String secondProductCategoryName = (String) secondProductCategory.get("name");
        System.out.println("secondProductCategoryName = " + secondProductCategoryName);

    }
}
