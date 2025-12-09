package com.restassured.practice.tests;

import com.restassured.practice.models.Post;
import com.restassured.practice.models.ReqResUser;
import com.restassured.practice.models.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Serialization and Deserialization Examples
 * Topics: POJO to JSON, JSON to POJO, Complex object handling
 */
public class SerializationDeserializationTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "Serialization - POJO to JSON (POST)")
    public void testSerializationPostToPOJO() {
        Post post = Post.builder()
                .title("Test Serialization")
                .body("This is a test of POJO serialization")
                .userId(1)
                .build();

        given()
            .contentType(ContentType.JSON)
            .body(post)
            .log().body()
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("Test Serialization"))
            .body("body", equalTo("This is a test of POJO serialization"));
    }

    @Test(description = "Deserialization - JSON to POJO (GET)")
    public void testDeserializationJsonToPOJO() {
        Post post = 
            given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .extract()
                .as(Post.class);

        System.out.println("Deserialized Post:");
        System.out.println("ID: " + post.getId());
        System.out.println("Title: " + post.getTitle());
        System.out.println("Body: " + post.getBody());
        System.out.println("User ID: " + post.getUserId());

        assert post.getId() == 1;
        assert post.getTitle() != null;
        assert post.getUserId() > 0;
    }

    @Test(description = "Deserialization - Array of objects")
    public void testDeserializeArrayOfPosts() {
        Post[] posts = 
            given()
                .queryParam("userId", 1)
                .queryParam("_limit", 5)
            .when()
                .get("/posts")
            .then()
                .statusCode(200)
                .extract()
                .as(Post[].class);

        System.out.println("Number of posts retrieved: " + posts.length);
        
        for (Post post : posts) {
            System.out.println("Post ID: " + post.getId() + ", Title: " + post.getTitle());
            assert post.getUserId() == 1;
        }

        assert posts.length > 0 && posts.length <= 5;
    }

    @Test(description = "Serialization and Deserialization with ReqRes")
    public void testSerDeserWithReqRes() {
        RestAssured.baseURI = "https://reqres.in/api";

        // Create user (Serialization)
        User newUser = User.builder()
                .name("Jane Doe")
                .job("Software Engineer")
                .build();

        User createdUser = 
            given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .log().body()
            .when()
                .post("/users")
            .then()
                .log().body()
                .statusCode(201)
                .extract()
                .as(User.class);

        System.out.println("Created User ID: " + createdUser.getId());
        System.out.println("Created User Name: " + createdUser.getName());
        
        assert createdUser.getName().equals("Jane Doe");
        assert createdUser.getJob().equals("Software Engineer");
        assert createdUser.getId() != null;
    }

    @Test(description = "Deserialize ReqRes user data")
    public void testDeserializeReqResUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        ReqResUser user = 
            given()
                .pathParam("id", 2)
            .when()
                .get("/users/{id}")
            .then()
                .statusCode(200)
                .extract()
                .path("data");

        System.out.println("User Details:");
        System.out.println("ID: " + user.getId());
        System.out.println("Email: " + user.getEmail());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());

        assert user.getId() == 2;
        assert user.getEmail() != null;
        assert user.getFirstName() != null;
    }

    @Test(description = "Update using Serialization (PUT)")
    public void testUpdateUsingSerialization() {
        RestAssured.baseURI = "https://reqres.in/api";

        User updateUser = User.builder()
                .name("Updated Name")
                .job("Lead Engineer")
                .build();

        User updatedResponse = 
            given()
                .contentType(ContentType.JSON)
                .body(updateUser)
                .pathParam("id", 2)
            .when()
                .put("/users/{id}")
            .then()
                .statusCode(200)
                .extract()
                .as(User.class);

        System.out.println("Updated User Name: " + updatedResponse.getName());
        System.out.println("Updated User Job: " + updatedResponse.getJob());
        
        assert updatedResponse.getName().equals("Updated Name");
        assert updatedResponse.getJob().equals("Lead Engineer");
    }

    @Test(description = "Complex nested object deserialization")
    public void testComplexObjectDeserialization() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Using JsonPath to navigate nested structure
        String city = 
            given()
            .when()
                .get("/users/1")
            .then()
                .statusCode(200)
                .extract()
                .path("address.city");

        System.out.println("User's City: " + city);
        assert city != null;
    }

    @Test(description = "Partial deserialization with JsonPath")
    public void testPartialDeserialization() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Post post = 
            given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .extract()
                .as(Post.class);

        // Only specific fields are deserialized into POJO
        System.out.println("Post Title: " + post.getTitle());
        
        assert post.getId() != null;
        assert post.getTitle() != null;
    }
}
