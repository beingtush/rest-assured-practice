package com.restassured.practice.tests;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Headers and Cookies Examples
 * Topics: Custom headers, Cookie handling, Content-Type negotiation
 */
public class HeadersAndCookiesTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://httpbin.org";
    }

    @Test(description = "Send single custom header")
    public void testSingleCustomHeader() {
        given()
            .header("Custom-Header", "CustomValue")
            .log().all()
        .when()
            .get("/headers")
        .then()
            .log().all()
            .statusCode(200)
            .body("headers.Custom-Header", equalTo("CustomValue"));
    }

    @Test(description = "Send multiple headers")
    public void testMultipleHeaders() {
        given()
            .header("X-Api-Key", "test-api-key-12345")
            .header("X-Request-Id", "req-001")
            .header("User-Agent", "RestAssured-Testing")
        .when()
            .get("/headers")
        .then()
            .statusCode(200)
            .body("headers.X-Api-Key", equalTo("test-api-key-12345"))
            .body("headers.X-Request-Id", equalTo("req-001"))
            .body("headers.User-Agent", equalTo("RestAssured-Testing"));
    }

    @Test(description = "Headers using HashMap")
    public void testHeadersWithHashMap() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer fake-token-12345");

        given()
            .headers(headers)
            .log().headers()
        .when()
            .get("/headers")
        .then()
            .statusCode(200)
            .body("headers.Authorization", equalTo("Bearer fake-token-12345"));
    }

    @Test(description = "Content-Type header")
    public void testContentTypeHeader() {
        given()
            .contentType("application/json")
        .when()
            .get("/headers")
        .then()
            .statusCode(200)
            .body("headers.Content-Type", equalTo("application/json"));
    }

    @Test(description = "Accept header")
    public void testAcceptHeader() {
        given()
            .accept("application/xml")
        .when()
            .get("/headers")
        .then()
            .statusCode(200)
            .body("headers.Accept", containsString("application/xml"));
    }

    @Test(description = "Extract response headers")
    public void testExtractResponseHeaders() {
        Headers responseHeaders = 
            given()
            .when()
                .get("/headers")
            .then()
                .statusCode(200)
                .extract()
                .headers();

        System.out.println("Response Headers:");
        for (Header header : responseHeaders) {
            System.out.println(header.getName() + ": " + header.getValue());
        }

        // Validate specific header
        String contentType = responseHeaders.getValue("Content-Type");
        assert contentType.contains("application/json");
    }

    @Test(description = "Send single cookie")
    public void testSingleCookie() {
        given()
            .cookie("session-id", "abc123xyz")
            .log().all()
        .when()
            .get("/cookies")
        .then()
            .log().all()
            .statusCode(200)
            .body("cookies.session-id", equalTo("abc123xyz"));
    }

    @Test(description = "Send multiple cookies")
    public void testMultipleCookies() {
        given()
            .cookie("user-id", "user123")
            .cookie("token", "token-xyz-789")
            .cookie("preferences", "dark-mode")
        .when()
            .get("/cookies")
        .then()
            .statusCode(200)
            .body("cookies.user-id", equalTo("user123"))
            .body("cookies.token", equalTo("token-xyz-789"))
            .body("cookies.preferences", equalTo("dark-mode"));
    }

    @Test(description = "Cookies using HashMap")
    public void testCookiesWithHashMap() {
        Map<String, String> cookies = new HashMap<>();
        cookies.put("session", "session-12345");
        cookies.put("user", "testuser");

        given()
            .cookies(cookies)
        .when()
            .get("/cookies")
        .then()
            .statusCode(200)
            .body("cookies.session", equalTo("session-12345"))
            .body("cookies.user", equalTo("testuser"));
    }

    @Test(description = "Extract response cookies")
    public void testExtractResponseCookies() {
        given()
            .queryParam("name", "test-cookie")
            .queryParam("value", "test-value")
        .when()
            .get("/cookies/set")
        .then()
            .statusCode(200);

        // Extracting cookies from response
        Map<String, String> responseCookies = 
            given()
            .when()
                .get("/cookies")
            .then()
                .statusCode(200)
                .extract()
                .cookies();

        System.out.println("Response Cookies: " + responseCookies);
    }

    @Test(description = "Set and verify cookies")
    public void testSetAndVerifyCookies() {
        // Set cookies
        given()
            .queryParam("test-cookie", "test-value")
        .when()
            .get("/cookies/set")
        .then()
            .statusCode(200)
            .cookie("test-cookie", equalTo("test-value"));
    }

    @Test(description = "Custom User-Agent header")
    public void testCustomUserAgent() {
        given()
            .header("User-Agent", "RestAssured/5.0 (API Testing)")
        .when()
            .get("/user-agent")
        .then()
            .statusCode(200)
            .body("user-agent", equalTo("RestAssured/5.0 (API Testing)"));
    }

    @Test(description = "Authorization header")
    public void testAuthorizationHeader() {
        given()
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
        .when()
            .get("/headers")
        .then()
            .statusCode(200)
            .body("headers.Authorization", equalTo("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"));
    }

    @Test(description = "Validate response header values")
    public void testValidateResponseHeaders() {
        given()
        .when()
            .get("/response-headers?Custom-Header=TestValue")
        .then()
            .statusCode(200)
            .header("Custom-Header", equalTo("TestValue"))
            .header("Content-Type", containsString("application/json"));
    }
}
