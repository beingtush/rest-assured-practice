package com.restassured.practice.tests;

import com.restassured.practice.models.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * POST Request Examples
 * Topics: Create resources, Request body formats, JSON handling
 */
public class PostRequestTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "POST request with String body")
    public void testPostWithStringBody() {
        String requestBody = "{\n" +
                "  \"title\": \"Test Post\",\n" +
                "  \"body\": \"This is a test post body\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("Test Post"))
            .body("body", equalTo("This is a test post body"))
            .body("userId", equalTo(1))
            .body("id", notNullValue());
    }

    @Test(description = "POST request with HashMap")
    public void testPostWithHashMap() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "HashMap Post");
        requestBody.put("body", "Post created using HashMap");
        requestBody.put("userId", 5);

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("HashMap Post"))
            .body("userId", equalTo(5))
            .body("id", notNullValue());
    }

    @Test(description = "POST request with POJO")
    public void testPostWithPOJO() {
        Post post = Post.builder()
                .title("POJO Post")
                .body("This post is created using POJO")
                .userId(10)
                .build();

        given()
            .contentType(ContentType.JSON)
            .body(post)
            .log().body()
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("POJO Post"))
            .body("body", equalTo("This post is created using POJO"))
            .body("userId", equalTo(10))
            .body("id", notNullValue());
    }

    @Test(description = "POST to ReqRes API - Create user")
    public void testCreateUserReqRes() {
        RestAssured.baseURI = "https://reqres.in/api";

        Map<String, String> user = new HashMap<>();
        user.put("name", "John Doe");
        user.put("job", "QA Engineer");

        given()
            .contentType(ContentType.JSON)
            .body(user)
            .log().all()
        .when()
            .post("/users")
        .then()
            .log().all()
            .statusCode(201)
            .body("name", equalTo("John Doe"))
            .body("job", equalTo("QA Engineer"))
            .body("id", notNullValue())
            .body("createdAt", notNullValue());
    }

    @Test(description = "POST with form parameters")
    public void testPostWithFormParams() {
        RestAssured.baseURI = "https://httpbin.org";

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("name", "Test User")
            .formParam("email", "test@example.com")
            .formParam("age", 25)
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("form.name", equalTo("Test User"))
            .body("form.email", equalTo("test@example.com"))
            .body("form.age", equalTo("25"));
    }

    @Test(description = "POST with multipart form data")
    public void testPostWithMultipartFormData() {
        RestAssured.baseURI = "https://httpbin.org";

        given()
            .multiPart("name", "John Smith")
            .multiPart("city", "New York")
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .body("form.name", equalTo("John Smith"))
            .body("form.city", equalTo("New York"));
    }

    @Test(description = "Extract response after POST")
    public void testExtractResponseAfterPost() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Post post = Post.builder()
                .title("Extract Test")
                .body("Testing response extraction")
                .userId(1)
                .build();

        Integer createdId = 
            given()
                .contentType(ContentType.JSON)
                .body(post)
            .when()
                .post("/posts")
            .then()
                .statusCode(201)
                .extract()
                .path("id");

        System.out.println("Created Post ID: " + createdId);
        assert createdId != null && createdId > 0;
    }
}
