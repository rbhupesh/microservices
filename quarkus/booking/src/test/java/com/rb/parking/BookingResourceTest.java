package com.rb.parking;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookingResourceTest {

    @Test
    public void testHelloEndpoint() {
    	System.out.println("<<<<<testHelloEndpoint>>>>>");
        given()
          .when().get("/parkings")
          .then()
             .statusCode(200)
             .body(is("Hello Bookings"));
    }
    
    @Test
    public void testAllBookingsEndpoint() {
    	System.out.println("<<<<<testBookingsEndpoint>>>>>");
        given()
          .when().get("/parkings/api/v2/bookings")
          .then()
             .statusCode(200);
    }
    
    @Test
    public void testBookingsByCriteriaEndpoint() {
    	System.out.println("<<<<<testBookingsEndpoint>>>>>");
        given()
          .when().get("/parkings/api/v2/bookings/14-Oct-2020/08")
          .then()
             .statusCode(200);
    }
    
    @Test
    public void testAddBookingsEndpoint() {
    	System.out.println("<<<<<testAddBookingsEndpoint>>>>>");
        given()
          .when().get("/parkings/api/v2/bookings/02-Mar-2021/13/nidhi")
          .then()
             .statusCode(200);
        System.out.println("<<<<<testAddBookingsEndpoint STOPS>>>>>");
    }

}