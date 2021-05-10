package com.rb.parking;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class OrderResourceTest {
    
    @Test
    public void testAddOrdersEndpoint() {
    	System.out.println("<<<<<testAddOrdersEndpoint>>>>>");
        given()
          .when().get("/ecommerce/api/v2/orders/rbhupesh/sa70")
          .then()
             .statusCode(200);
        System.out.println("\n<<<<<testAddOrdersEndpoint STOPS>>>>>");
    }

}