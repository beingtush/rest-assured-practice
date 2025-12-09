package com.restassured.practice.tests;

import com.restassured.practice.utils.ApiConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Test class demonstrating advanced Request and Response Specifications
 */
public class AdvancedRequestSpecTest {

    @Test(description = "Test using reusable request specification for JSONPlaceholder")
    public void testWithJsonPlaceholderSpec() {
        given()
            .spec(ApiConfig.getJsonPlaceholderRequestSpec())
        .when()
            .get("/users/1")
        .then()
            .spec(ApiConfig.getSuccessResponseSpec())
            .body("id", equalTo(1))
            .body("name", notNullValue());
    }

    @Test(description = "Test using reusable request specification for ReqRes")
    public void testWithReqResSpec() {
        given()
            .spec(ApiConfig.getReqResRequestSpec())
        .when()
            .get("/users?page=1")
        .then()
            .spec(ApiConfig.getSuccessResponseSpec())
            .body("data", not(empty()))
            .body("page", equalTo(1));
    }

    @Test(description = "Test using reusable request specification for HTTPBin")
    public void testWithHttpBinSpec() {
        given()
            .spec(ApiConfig.getHttpBinRequestSpec())
            .queryParam("test", "value")
        .when()
            .get("/get")
        .then()
            .spec(ApiConfig.getSuccessResponseSpec())
            .body("args.test", equalTo("value"));
    }

    @Test(description = "Test POST request with created response specification")
    public void testPostWithCreatedSpec() {
        String requestBody = """
            {
                "name": "Test User",
                "job": "QA Engineer"
            }
            """;

        given()
            .spec(ApiConfig.getReqResRequestSpec())
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .spec(ApiConfig.getCreatedResponseSpec())
            .body("name", equalTo("Test User"))
            .body("job", equalTo("QA Engineer"))
            .body("id", notNullValue())
            .body("createdAt", notNullValue());
    }

    @Test(description = "Test extracting response and reusing in another request")
    public void testExtractAndReuseResponse() {
        // First request - extract user ID
        Response response = given()
            .spec(ApiConfig.getJsonPlaceholderRequestSpec())
        .when()
            .get("/users/1")
        .then()
            .spec(ApiConfig.getSuccessResponseSpec())
            .extract()
            .response();

        int userId = response.path("id");
        String userName = response.path("name");

        System.out.println("Extracted User ID: " + userId);
        System.out.println("Extracted User Name: " + userName);

        // Second request - use extracted user ID
        given()
            .spec(ApiConfig.getJsonPlaceholderRequestSpec())
            .queryParam("userId", userId)
        .when()
            .get("/posts")
        .then()
            .spec(ApiConfig.getSuccessResponseSpec())
            .body("[0].userId", equalTo(userId));
    }

    @Test(description = "Test chaining multiple requests")
    public void testRequestChaining() {
        // Create a post
        String postId = given()
            .spec(ApiConfig.getJsonPlaceholderRequestSpec())
            .body("""
                {
                    "title": "Test Post",
                    "body": "Test Body",
                    "userId": 1
                }
                """)
        .when()
            .post("/posts")
        .then()
            .spec(ApiConfig.getCreatedResponseSpec())
            .extract()
            .path("id")
            .toString();

        // Get the created post
        given()
            .spec(ApiConfig.getJsonPlaceholderRequestSpec())
        .when()
            .get("/posts/" + postId)
        .then()
            .spec(ApiConfig.getSuccessResponseSpec())
            .body("id", equalTo(Integer.parseInt(postId)))
            .body("title", equalTo("Test Post"));
    }

    @Test(description = "Test REST Countries API with custom spec")
    public void testRestCountriesApi() {
        given()
            .spec(ApiConfig.getRestCountriesRequestSpec())
        .when()
            .get("/name/india")
        .then()
            .spec(ApiConfig.getSuccessResponseSpec())
            .body("[0].name.common", equalToIgnoringCase("India"))
            .body("[0].capital[0]", equalTo("New Delhi"))
            .body("[0].region", equalTo("Asia"));
    }
}
