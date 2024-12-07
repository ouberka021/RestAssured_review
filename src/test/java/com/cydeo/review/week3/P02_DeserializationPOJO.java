package com.cydeo.review.week3;

import com.cydeo.Utils.FakeStoreTestBase;
import com.cydeo.pojo.CategoryPOJO;
import com.cydeo.pojo.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class P02_DeserializationPOJO extends FakeStoreTestBase {
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

        JsonPath jp = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .queryParam("limit", 10)
                .queryParam("offset", 0)
                .when().get("/categories/{id}/products")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.withCharset("utf-8"))
                .extract().jsonPath();
        
      System.out.println("====== GET FIRST PRODUCTS ======");
        Product firstProduct = jp.getObject("[0]",Product.class);
        System.out.println("first Product = " + firstProduct);

      System.out.println("====== GET FIRST IMAGES ======");
      List<String> images = firstProduct.getAllImages();
        System.out.println("images = " + images);

      System.out.println("====== GET FIRST PRODUCT CATEGORY ======");
     CategoryPOJO category = firstProduct.getCategory();

      System.out.println("====== GET ALL PRODUCTS ======");
      List<Product> allProducts = jp.getList("", Product.class);
      System.out.println("all Products = " + allProducts);

      System.out.println("====== GET SECOND PRODUCTS ======");
      Product secondProduct = allProducts.get(1);
      System.out.println("second Product = " + secondProduct);

      System.out.println("====== GET SECOND PRODUCTS PRICE ======");
      int price = secondProduct.getPrice();
      System.out.println("price = " + price);

      System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
      List<String> secondProductImages = secondProduct.getAllImages();
      System.out.println("secondProductImages = " + secondProductImages);

      System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
      String firstImage = secondProductImages.get(0);
      System.out.println("firstImage = " + firstImage);

      System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
      CategoryPOJO secondCategory = secondProduct.getCategory();
      System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
      String categoryName = secondCategory.getName();
      System.out.println("categoryName = " + categoryName);
        
    }
    

}
