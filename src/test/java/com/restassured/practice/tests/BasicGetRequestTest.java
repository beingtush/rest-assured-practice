package com.restassured.practice.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Basic GET Request Examples
 * Topics: Simple GET, Response validation, Status codes, Response time
 */
public class BasicGetRequestTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "Simple GET request - Get all posts")
    public void testGetAllPosts() {
        given()
            .log().all()
        .when()
            .get("/posts")
        .then()
            .log().all()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test(description = "Get a single post by ID")
    public void testGetSinglePost() {
        given()
            .pathParam("id", 1)
                .log().all()
        .when()
            .get("/posts/{id}")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", notNullValue())
            .body("title", not(emptyString()))
            .body("body", not(emptyString()));
    }

    @Test(description = "Validate response time")
    public void testResponseTime() {
        given()
        .when()
            .get("/posts/1")
        .then()
            .time(lessThan(3000L))
            .statusCode(200);
    }

    @Test(description = "Extract and validate response data")
    public void testExtractResponseData() {
        Response response = 
            given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .extract().response();

        // Extract specific fields
        int userId = response.path("userId");
        String title = response.path("title");
        
        System.out.println("User ID: " + userId);
        System.out.println("Title: " + title);
        
        // Assertions
        assert userId > 0;
        assert title != null && !title.isEmpty();
    }

    @Test(description = "Get all comments for a post")
    public void testGetCommentsForPost() {
        Response response =
        given()
            .pathParam("postId", 1)
        .when()
            .get("/posts/{postId}/comments")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].postId", equalTo(1))
            .body("[0].email", containsString("@"))
                .extract().response();

        String firstEmail = response.path("[0].email");
        String secondEmail = response.path("[1].email");
        System.out.println(firstEmail);
        System.out.println(secondEmail);

    }

    @Test(description = "Validate JSON array response")
    public void testValidateJsonArray() {
        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("size()", equalTo(10))
            .body("id", everyItem(notNullValue()))
            .body("name", everyItem(not(emptyString())))
            .body("email", everyItem(containsString("@")));
    }

    @Test(description = "Validate nested JSON fields")
    public void testNestedJsonValidation() {
        given()
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
                .log().all()
            .body("id", equalTo(1))
            .body("address.street", equalToIgnoringCase("Kulas light"))
            .body("address.street", containsString("Kulas"))
            .body("address.city", notNullValue())
            .body("address.geo.lat", notNullValue())
            .body("company.name", not(emptyString()));
    }

    @Test(description = "Test 404 - Resource not found")
    public void testResourceNotFound() {
        given()
        .when()
                .log().all()
            .get("/posts/9999")
        .then()
                .log().all()
            .statusCode(404);
    }
}
