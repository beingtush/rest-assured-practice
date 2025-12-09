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
 * PUT, PATCH, DELETE Request Examples
 * Topics: Update operations, Partial updates, Delete operations
 */
public class PutPatchDeleteTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "PUT request - Complete update")
    public void testPutRequest() {
        Post updatedPost = Post.builder()
                .id(1)
                .title("Updated Post Title")
                .body("This post has been completely updated")
                .userId(1)
                .build();

        given()
            .contentType(ContentType.JSON)
            .body(updatedPost)
            .pathParam("id", 1)
            .log().all()
        .when()
            .put("/posts/{id}")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", equalTo("Updated Post Title"))
            .body("body", equalTo("This post has been completely updated"))
            .body("userId", equalTo(1));
    }

    @Test(description = "PUT with HashMap")
    public void testPutWithHashMap() {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("id", 1);
        updateData.put("title", "HashMap Updated Title");
        updateData.put("body", "Updated using HashMap");
        updateData.put("userId", 1);

        given()
            .contentType(ContentType.JSON)
            .body(updateData)
            .pathParam("id", 1)
        .when()
            .put("/posts/{id}")
        .then()
            .statusCode(200)
            .body("title", equalTo("HashMap Updated Title"))
            .body("body", equalTo("Updated using HashMap"));
    }

    @Test(description = "PATCH request - Partial update")
    public void testPatchRequest() {
        Map<String, Object> partialUpdate = new HashMap<>();
        partialUpdate.put("title", "Partially Updated Title");

        given()
            .contentType(ContentType.JSON)
            .body(partialUpdate)
            .pathParam("id", 1)
            .log().all()
        .when()
            .patch("/posts/{id}")
        .then()
            .log().all()
            .statusCode(200)
            .body("title", equalTo("Partially Updated Title"))
            .body("userId", notNullValue())  // Other fields remain
            .body("id", equalTo(1));
    }

    @Test(description = "PATCH - Update only specific fields")
    public void testPatchSpecificFields() {
        Map<String, String> update = new HashMap<>();
        update.put("body", "Only body is updated via PATCH");

        given()
            .contentType(ContentType.JSON)
            .body(update)
            .pathParam("id", 5)
        .when()
            .patch("/posts/{id}")
        .then()
            .statusCode(200)
            .body("body", equalTo("Only body is updated via PATCH"))
            .body("id", equalTo(5));
    }

    @Test(description = "DELETE request")
    public void testDeleteRequest() {
        given()
            .pathParam("id", 1)
            .log().all()
        .when()
            .delete("/posts/{id}")
        .then()
            .log().all()
            .statusCode(200);
    }

    @Test(description = "DELETE and verify")
    public void testDeleteAndVerify() {
        int postIdToDelete = 10;

        // Delete the post
        given()
            .pathParam("id", postIdToDelete)
        .when()
            .delete("/posts/{id}")
        .then()
            .statusCode(200);

        System.out.println("Post " + postIdToDelete + " deleted successfully");
    }

    @Test(description = "PUT request with ReqRes API")
    public void testPutReqRes() {
        RestAssured.baseURI = "https://reqres.in/api";

        Map<String, String> updateUser = new HashMap<>();
        updateUser.put("name", "Updated Name");
        updateUser.put("job", "Senior QA Engineer");

        given()
            .contentType(ContentType.JSON)
            .body(updateUser)
            .pathParam("id", 2)
        .when()
            .put("/users/{id}")
        .then()
            .statusCode(200)
            .body("name", equalTo("Updated Name"))
            .body("job", equalTo("Senior QA Engineer"))
            .body("updatedAt", notNullValue());
    }

    @Test(description = "PATCH request with ReqRes API")
    public void testPatchReqRes() {
        RestAssured.baseURI = "https://reqres.in/api";

        Map<String, String> patchData = new HashMap<>();
        patchData.put("job", "Automation Architect");

        given()
            .contentType(ContentType.JSON)
            .body(patchData)
            .pathParam("id", 2)
        .when()
            .patch("/users/{id}")
        .then()
            .statusCode(200)
            .body("job", equalTo("Automation Architect"))
            .body("updatedAt", notNullValue());
    }
}
