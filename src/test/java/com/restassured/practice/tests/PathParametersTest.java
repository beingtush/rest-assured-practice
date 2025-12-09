package com.restassured.practice.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Path Parameters Examples
 * Topics: Dynamic URL paths, Path variable substitution
 */
public class PathParametersTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "Single path parameter")
    public void testSinglePathParameter() {
        given()
            .pathParam("id", 1)
            .log().all()
        .when()
            .get("/posts/{id}")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", equalTo(1));
    }

    @Test(description = "Multiple path parameters")
    public void testMultiplePathParameters() {
        given()
            .pathParam("postId", 1)
            .pathParam("commentId", 1)
        .when()
            .get("/posts/{postId}/comments/{commentId}")
        .then()
            .statusCode(404); // This endpoint doesn't exist, just demonstrating syntax
    }

    @Test(description = "Path params with HashMap")
    public void testPathParamsWithHashMap() {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("userId", 1);

        given()
            .pathParams(pathParams)
        .when()
            .get("/users/{userId}")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", notNullValue());
    }

    @Test(description = "Get user posts using path parameter")
    public void testGetUserPosts() {
        int userId = 2;

        given()
            .pathParam("userId", userId)
        .when()
            .get("/users/{userId}/posts")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("userId", everyItem(equalTo(userId)));
    }

    @Test(description = "Get specific comment for a post")
    public void testGetPostComments() {
        given()
            .pathParam("postId", 1)
        .when()
            .get("/posts/{postId}/comments")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("postId", everyItem(equalTo(1)));
    }

    @Test(description = "Path parameter with ReqRes API")
    public void testPathParamReqRes() {
        RestAssured.baseURI = "https://reqres.in/api";

        given()
            .pathParam("id", 2)
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", notNullValue())
            .body("data.first_name", notNullValue());
    }

    @Test(description = "Dynamic path with variable substitution")
    public void testDynamicPath() {
        int postId = 5;

        given()
            .pathParam("id", postId)
            .log().uri()
        .when()
            .get("/posts/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(postId));
    }

    @Test(description = "Path parameter - Delete operation")
    public void testDeleteWithPathParam() {
        given()
            .pathParam("id", 10)
        .when()
            .delete("/posts/{id}")
        .then()
            .statusCode(200);
    }

    @Test(description = "Nested path parameters")
    public void testNestedPathParams() {
        given()
            .pathParam("postId", 1)
        .when()
            .get("/posts/{postId}/comments")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].postId", equalTo(1))
            .body("[0].id", notNullValue());
    }

    @Test(description = "Path param with REST Countries API")
    public void testPathParamRestCountries() {
        RestAssured.baseURI = "https://restcountries.com/v3.1";

        given()
            .pathParam("name", "france")
        .when()
            .get("/name/{name}")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].name.common", equalToIgnoringCase("France"));
    }
}
