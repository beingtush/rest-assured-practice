package com.restassured.practice.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Authentication Examples
 * Topics: Basic Auth, Bearer Token, API Key authentication
 */
public class AuthenticationTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://httpbin.org";
    }

    @Test(description = "Basic Authentication - Success")
    public void testBasicAuthSuccess() {
        given()
            .auth().basic("user", "passwd")
            .log().all()
        .when()
            .get("/basic-auth/user/passwd")
        .then()
            .log().all()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .body("user", equalTo("user"));
    }

    @Test(description = "Basic Authentication - Failure")
    public void testBasicAuthFailure() {
        given()
            .auth().basic("user", "wrongpassword")
        .when()
            .get("/basic-auth/user/passwd")
        .then()
            .statusCode(401);
    }

    @Test(description = "Preemptive Basic Authentication")
    public void testPreemptiveBasicAuth() {
        given()
            .auth().preemptive().basic("testuser", "testpass")
        .when()
            .get("/hidden-basic-auth/testuser/testpass")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true));
    }

    @Test(description = "Bearer Token Authentication")
    public void testBearerToken() {
        String token = "test-bearer-token-12345";

        given()
            .header("Authorization", "Bearer " + token)
            .log().headers()
        .when()
            .get("/bearer")
        .then()
            .log().all()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .body("token", equalTo(token));
    }

    @Test(description = "Bearer Token - Using auth() method")
    public void testBearerTokenUsingAuth() {
        given()
            .auth().oauth2("test-oauth-token-xyz")
        .when()
            .get("/bearer")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true));
    }

    @Test(description = "API Key in Header")
    public void testApiKeyInHeader() {
        given()
            .header("X-API-Key", "my-secret-api-key")
        .when()
            .get("/headers")
        .then()
            .statusCode(200)
            .body("headers.X-Api-Key", equalTo("my-secret-api-key"));
    }

    @Test(description = "API Key in Query Parameter")
    public void testApiKeyInQueryParam() {
        given()
            .queryParam("api_key", "12345-abcde-67890")
        .when()
            .get("/get")
        .then()
            .statusCode(200)
            .body("args.api_key", equalTo("12345-abcde-67890"));
    }

    @Test(description = "Digest Authentication")
    public void testDigestAuth() {
        given()
            .auth().digest("user", "passwd")
        .when()
            .get("/digest-auth/auth/user/passwd")
        .then()
            .statusCode(200)
            .body("authenticated", equalTo(true))
            .body("user", equalTo("user"));
    }

    @Test(description = "No Authentication - Unauthorized")
    public void testNoAuthentication() {
        given()
        .when()
            .get("/basic-auth/user/passwd")
        .then()
            .statusCode(401);
    }

    @Test(description = "Authentication with ReqRes - Register")
    public void testReqResRegister() {
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = "{\n" +
                "  \"email\": \"eve.holt@reqres.in\",\n" +
                "  \"password\": \"pistol\"\n" +
                "}";

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/register")
        .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("token", notNullValue());
    }

    @Test(description = "Authentication with ReqRes - Login")
    public void testReqResLogin() {
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = "{\n" +
                "  \"email\": \"eve.holt@reqres.in\",\n" +
                "  \"password\": \"cityslicka\"\n" +
                "}";

        String token = 
            given()
                .contentType("application/json")
                .body(requestBody)
            .when()
                .post("/login")
            .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .path("token");

        System.out.println("Login Token: " + token);
        assert token != null && !token.isEmpty();
    }

    @Test(description = "Failed Login - Missing Password")
    public void testFailedLogin() {
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = "{\n" +
                "  \"email\": \"peter@klaven\"\n" +
                "}";

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/login")
        .then()
            .statusCode(400)
            .body("error", equalTo("Missing password"));
    }
}
