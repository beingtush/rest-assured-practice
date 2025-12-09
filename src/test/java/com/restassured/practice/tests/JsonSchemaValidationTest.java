package com.restassured.practice.tests;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * JSON Schema Validation Examples
 * Topics: Schema validation, Structure verification
 */
public class JsonSchemaValidationTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "Validate JSON schema for single user")
    public void testValidateUserSchema() {
        given()
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }

    @Test(description = "Validate JSON schema for posts array")
    public void testValidatePostsSchema() {
        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/posts-schema.json"));
    }

    @Test(description = "Validate single post schema")
    public void testValidateSinglePostSchema() {
        given()
            .pathParam("id", 1)
        .when()
            .get("/posts/{id}")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }

    @Test(description = "Validate ReqRes user schema")
    public void testValidateReqResUserSchema() {
        RestAssured.baseURI = "https://reqres.in/api";

        given()
            .pathParam("id", 2)
        .when()
            .get("/users/{id}")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/reqres-user-schema.json"));
    }

    @Test(description = "Validate comments schema")
    public void testValidateCommentsSchema() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
            .queryParam("postId", 1)
        .when()
            .get("/comments")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/comments-schema.json"));
    }
}
