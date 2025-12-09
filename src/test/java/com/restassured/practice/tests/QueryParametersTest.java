package com.restassured.practice.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Query Parameters Examples
 * Topics: Single/multiple parameters, Parameter encoding, Filtering
 */
public class QueryParametersTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "Single query parameter")
    public void testSingleQueryParameter() {
        given()
            .queryParam("userId", 1)
            .log().all()
        .when()
            .get("/posts")
        .then()
            .log().all()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("userId", everyItem(equalTo(1)));
    }

    @Test(description = "Multiple query parameters")
    public void testMultipleQueryParameters() {
        given()
            .queryParam("userId", 1)
            .queryParam("id", 1)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", equalTo(1))
            .body("[0].userId", equalTo(1))
            .body("[0].id", equalTo(1));
    }

    @Test(description = "Query parameters using HashMap")
    public void testQueryParamsWithHashMap() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("userId", 2);
        queryParams.put("_limit", 5);

        given()
            .queryParams(queryParams)
            .log().all()
        .when()
            .get("/posts")
        .then()
            .log().all()
            .statusCode(200)
            .body("size()", lessThanOrEqualTo(5))
            .body("userId", everyItem(equalTo(2)));
    }

    @Test(description = "Filter posts by userId")
    public void testFilterByUserId() {
        given()
            .queryParam("userId", 3)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("userId", everyItem(equalTo(3)))
            .body("size()", greaterThan(0));
    }

    @Test(description = "Get comments for a specific post using query param")
    public void testGetCommentsForPost() {
        given()
            .queryParam("postId", 1)
        .when()
            .get("/comments")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("postId", everyItem(equalTo(1)))
            .body("[0].email", notNullValue());
    }

    @Test(description = "Pagination with query parameters")
    public void testPaginationQueryParams() {
        given()
            .queryParam("_page", 1)
            .queryParam("_limit", 10)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", equalTo(10));
    }

    @Test(description = "Search with query parameters - HTTPBin")
    public void testQueryParamsHttpBin() {
        RestAssured.baseURI = "https://httpbin.org";

        given()
            .queryParam("search", "rest assured")
            .queryParam("filter", "api testing")
            .queryParam("page", 1)
            .log().all()
        .when()
            .get("/get")
        .then()
            .log().all()
            .statusCode(200)
            .body("args.search", equalTo("rest assured"))
            .body("args.filter", equalTo("api testing"))
            .body("args.page", equalTo("1"));
    }

    @Test(description = "Query parameter with special characters")
    public void testQueryParamWithSpecialChars() {
        RestAssured.baseURI = "https://httpbin.org";

        given()
            .queryParam("name", "John Doe")
            .queryParam("email", "john@example.com")
        .when()
            .get("/get")
        .then()
            .statusCode(200)
            .body("args.name", equalTo("John Doe"))
            .body("args.email", equalTo("john@example.com"));
    }

    @Test(description = "Multiple values for same parameter")
    public void testMultipleValuesForSameParam() {
        RestAssured.baseURI = "https://httpbin.org";

        given()
            .queryParam("id", 1, 2, 3)
            .log().all()
        .when()
            .get("/get")
        .then()
            .log().all()
            .statusCode(200)
            .body("args.id", hasItems("1", "2", "3"));
    }

    @Test(description = "Query params with ReqRes API")
    public void testQueryParamsReqRes() {
        RestAssured.baseURI = "https://reqres.in/api";

        given()
            .queryParam("page", 2)
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("data", notNullValue())
            .body("data.size()", greaterThan(0));
    }
}
