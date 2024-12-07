package com.cydeo.Utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FakeStoreTestBase {

    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI="https://api.escuelajs.co/api/v1";
        System.err.println("RestAssured is initialized");
    }

    @AfterAll
    public static void afterAll() {
        RestAssured.reset();
        System.err.println("RestAssured reseted");
    }

}
