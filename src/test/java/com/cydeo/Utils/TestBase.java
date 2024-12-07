package com.cydeo.Utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void init() {
        System.err.println("Setting up test environment...");
        RestAssured.baseURI = "http://18.209.59.60:1000/ords/hr";
    }


    @AfterAll
    public static void tearDown() {
        System.err.println("Cleaning up test environment...");
        RestAssured.reset();
    }

}
