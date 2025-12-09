package com.restassured.practice.tests;

import com.restassured.practice.models.User;
import com.restassured.practice.utils.ApiConfig;
import com.restassured.practice.utils.TestDataGenerator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Test class demonstrating data-driven testing with REST Assured
 */
public class DataDrivenTest {

    @DataProvider(name = "userIds")
    public Object[][] userIdsProvider() {
        return new Object[][] {
            {1}, {2}, {3}, {4}, {5}
        };
    }

    @DataProvider(name = "validStatusCodes")
    public Object[][] validStatusCodesProvider() {
        return new Object[][] {
            {200}, {201}, {204}
        };
    }

    @DataProvider(name = "endpoints")
    public Object[][] endpointsProvider() {
        return new Object[][] {
            {"/users", 200},
            {"/posts", 200},
            {"/comments", 200},
            {"/albums", 200},
            {"/todos", 200}
        };
    }

    @Test(dataProvider = "userIds", description = "Test getting users with different IDs")
    public void testGetUserWithDifferentIds(int userId) {
        given()
            .spec(ApiConfig.getJsonPlaceholderRequestSpec())
        .when()
            .get("/users/" + userId)
        .then()
            .spec(ApiConfig.getSuccessResponseSpec())
            .body("id", equalTo(userId))
            .body("name", notNullValue())
            .body("email", notNullValue());
    }

    @Test(dataProvider = "endpoints", description = "Test multiple endpoints")
    public void testMultipleEndpoints(String endpoint, int expectedStatusCode) {
        given()
            .spec(ApiConfig.getJsonPlaceholderRequestSpec())
        .when()
            .get(endpoint)
        .then()
            .statusCode(expectedStatusCode)
            .body("$", not(empty()));
    }

    @Test(dataProvider = "validStatusCodes", description = "Test HTTPBin status codes")
    public void testHttpBinStatusCodes(int statusCode) {
        given()
            .spec(ApiConfig.getHttpBinRequestSpec())
        .when()
            .get("/status/" + statusCode)
        .then()
            .statusCode(statusCode);
    }

    @Test(invocationCount = 5, description = "Test creating multiple users with random data")
    public void testCreateMultipleRandomUsers() {
        User randomUser = TestDataGenerator.generateRandomUser();

        given()
            .spec(ApiConfig.getJsonPlaceholderRequestSpec())
            .body(randomUser)
        .when()
            .post("/users")
        .then()
            .spec(ApiConfig.getCreatedResponseSpec())
            .body("name", equalTo(randomUser.getName()))
            .body("email", equalTo(randomUser.getEmail()));
    }
}
