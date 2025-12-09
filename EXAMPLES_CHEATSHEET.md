# RestAssured Examples Cheatsheet

Quick reference for all RestAssured concepts with examples.

---

## 1. Basic GET Request

```java
// Simple GET request
given()
.when()
    .get("https://jsonplaceholder.typicode.com/posts/1")
.then()
    .statusCode(200);
```

---

## 2. POST Request

```java
// POST with JSON body (String)
given()
    .contentType(ContentType.JSON)
    .body("{ \"title\": \"Test\", \"body\": \"Content\", \"userId\": 1 }")
.when()
    .post("https://jsonplaceholder.typicode.com/posts")
.then()
    .statusCode(201);

// POST with HashMap
Map<String, Object> requestBody = new HashMap<>();
requestBody.put("title", "Test");
requestBody.put("body", "Content");
requestBody.put("userId", 1);

given()
    .contentType(ContentType.JSON)
    .body(requestBody)
.when()
    .post("https://jsonplaceholder.typicode.com/posts")
.then()
    .statusCode(201);

// POST with POJO
Post post = Post.builder()
    .title("Test")
    .body("Content")
    .userId(1)
    .build();

given()
    .contentType(ContentType.JSON)
    .body(post)
.when()
    .post("https://jsonplaceholder.typicode.com/posts")
.then()
    .statusCode(201);
```

---

## 3. PUT Request (Complete Update)

```java
given()
    .contentType(ContentType.JSON)
    .body("{ \"id\": 1, \"title\": \"Updated\", \"body\": \"New content\", \"userId\": 1 }")
    .pathParam("id", 1)
.when()
    .put("/posts/{id}")
.then()
    .statusCode(200);
```

---

## 4. PATCH Request (Partial Update)

```java
given()
    .contentType(ContentType.JSON)
    .body("{ \"title\": \"Only Title Updated\" }")
    .pathParam("id", 1)
.when()
    .patch("/posts/{id}")
.then()
    .statusCode(200);
```

---

## 5. DELETE Request

```java
given()
    .pathParam("id", 1)
.when()
    .delete("/posts/{id}")
.then()
    .statusCode(200);
```

---

## 6. Query Parameters

```java
// Single query parameter
given()
    .queryParam("userId", 1)
.when()
    .get("/posts")
.then()
    .statusCode(200);

// Multiple query parameters
given()
    .queryParam("userId", 1)
    .queryParam("_limit", 5)
.when()
    .get("/posts")
.then()
    .statusCode(200);

// Using HashMap
Map<String, Object> params = new HashMap<>();
params.put("userId", 1);
params.put("_limit", 5);

given()
    .queryParams(params)
.when()
    .get("/posts")
.then()
    .statusCode(200);
```

---

## 7. Path Parameters

```java
// Single path parameter
given()
    .pathParam("id", 1)
.when()
    .get("/posts/{id}")
.then()
    .statusCode(200);

// Multiple path parameters
given()
    .pathParam("userId", 1)
    .pathParam("postId", 5)
.when()
    .get("/users/{userId}/posts/{postId}")
.then()
    .statusCode(200);
```

---

## 8. Headers

```java
// Single header
given()
    .header("Content-Type", "application/json")
.when()
    .get("/headers")
.then()
    .statusCode(200);

// Multiple headers
given()
    .header("Content-Type", "application/json")
    .header("Authorization", "Bearer token123")
.when()
    .get("/headers")
.then()
    .statusCode(200);

// Using HashMap
Map<String, String> headers = new HashMap<>();
headers.put("Content-Type", "application/json");
headers.put("Authorization", "Bearer token123");

given()
    .headers(headers)
.when()
    .get("/headers")
.then()
    .statusCode(200);
```

---

## 9. Cookies

```java
// Single cookie
given()
    .cookie("session-id", "abc123")
.when()
    .get("/cookies")
.then()
    .statusCode(200);

// Multiple cookies
given()
    .cookie("session-id", "abc123")
    .cookie("user-id", "user456")
.when()
    .get("/cookies")
.then()
    .statusCode(200);
```

---

## 10. Authentication

```java
// Basic Authentication
given()
    .auth().basic("username", "password")
.when()
    .get("/basic-auth/username/password")
.then()
    .statusCode(200);

// Preemptive Basic Auth
given()
    .auth().preemptive().basic("username", "password")
.when()
    .get("/hidden-basic-auth/username/password")
.then()
    .statusCode(200);

// Bearer Token
given()
    .auth().oauth2("your-token-here")
.when()
    .get("/bearer")
.then()
    .statusCode(200);

// API Key in Header
given()
    .header("X-API-Key", "your-api-key")
.when()
    .get("/api/data")
.then()
    .statusCode(200);

// Digest Authentication
given()
    .auth().digest("username", "password")
.when()
    .get("/digest-auth/auth/username/password")
.then()
    .statusCode(200);
```

---

## 11. Response Validation

```java
// Status code
.then()
    .statusCode(200)

// Content type
.then()
    .contentType(ContentType.JSON)

// Response body - single field
.then()
    .body("id", equalTo(1))
    .body("title", notNullValue())
    .body("email", containsString("@"))

// Response body - array
.then()
    .body("size()", greaterThan(0))
    .body("[0].id", equalTo(1))
    .body("id", everyItem(notNullValue()))

// Nested fields
.then()
    .body("address.city", equalTo("New York"))
    .body("address.geo.lat", notNullValue())

// Response time
.then()
    .time(lessThan(3000L))
```

---

## 12. Extract Response Data

```java
// Extract entire response
Response response = given()
    .when()
        .get("/posts/1")
    .then()
        .statusCode(200)
        .extract().response();

// Extract specific field
String title = given()
    .when()
        .get("/posts/1")
    .then()
        .extract().path("title");

// Extract as POJO
Post post = given()
    .when()
        .get("/posts/1")
    .then()
        .extract().as(Post.class);

// Extract array
Post[] posts = given()
    .when()
        .get("/posts")
    .then()
        .extract().as(Post[].class);
```

---

## 13. Serialization (POJO to JSON)

```java
Post post = Post.builder()
    .title("Test")
    .body("Content")
    .userId(1)
    .build();

given()
    .contentType(ContentType.JSON)
    .body(post)  // POJO automatically converted to JSON
.when()
    .post("/posts")
.then()
    .statusCode(201);
```

---

## 14. Deserialization (JSON to POJO)

```java
Post post = given()
    .when()
        .get("/posts/1")
    .then()
        .statusCode(200)
        .extract().as(Post.class);  // JSON automatically converted to POJO

System.out.println(post.getTitle());
System.out.println(post.getBody());
```

---

## 15. JSON Schema Validation

```java
given()
.when()
    .get("/users/1")
.then()
    .statusCode(200)
    .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
```

---

## 16. File Upload

```java
File file = new File("test.txt");

given()
    .multiPart("file", file)
.when()
    .post("/upload")
.then()
    .statusCode(200);

// With additional form data
given()
    .multiPart("file", file)
    .multiPart("description", "Test file")
.when()
    .post("/upload")
.then()
    .statusCode(200);
```

---

## 17. File Download

```java
byte[] fileBytes = given()
    .when()
        .get("/download/file.pdf")
    .then()
        .statusCode(200)
        .extract().asByteArray();
```

---

## 18. Logging

```java
// Log request
given()
    .log().all()  // Log everything
    .log().body()  // Log only body
    .log().headers()  // Log only headers
.when()
    .get("/posts/1")
.then()
    .statusCode(200);

// Log response
given()
.when()
    .get("/posts/1")
.then()
    .log().all()  // Log everything
    .log().body()  // Log only body
    .log().ifError()  // Log only if error
    .statusCode(200);
```

---

## 19. Base URI and Base Path

```java
// Set base URI for all requests
RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

// Now you can use relative paths
given()
.when()
    .get("/posts/1")  // Will use base URI
.then()
    .statusCode(200);

// Set base path
RestAssured.basePath = "/api/v1";
```

---

## 20. Request and Response Specifications

```java
// Request Specification
RequestSpecification requestSpec = new RequestSpecBuilder()
    .setBaseUri("https://jsonplaceholder.typicode.com")
    .setContentType(ContentType.JSON)
    .addHeader("Authorization", "Bearer token")
    .build();

given()
    .spec(requestSpec)
.when()
    .get("/posts/1")
.then()
    .statusCode(200);

// Response Specification
ResponseSpecification responseSpec = new ResponseSpecBuilder()
    .expectStatusCode(200)
    .expectContentType(ContentType.JSON)
    .build();

given()
.when()
    .get("/posts/1")
.then()
    .spec(responseSpec);
```

---

## 21. Hamcrest Matchers

```java
import static org.hamcrest.Matchers.*;

.then()
    .body("id", equalTo(1))
    .body("title", not(emptyString()))
    .body("email", containsString("@"))
    .body("age", greaterThan(18))
    .body("age", lessThan(100))
    .body("age", greaterThanOrEqualTo(18))
    .body("name", startsWith("John"))
    .body("name", endsWith("Doe"))
    .body("status", isIn(Arrays.asList("active", "pending")))
    .body("data", hasSize(10))
    .body("items", hasItem("apple"))
    .body("items", everyItem(notNullValue()))
    .body("data.id", everyItem(greaterThan(0)))
```

---

## 22. Form Parameters

```java
// URL encoded form
given()
    .contentType("application/x-www-form-urlencoded")
    .formParam("username", "john")
    .formParam("password", "secret")
.when()
    .post("/login")
.then()
    .statusCode(200);
```

---

## 23. Multipart Form Data

```java
given()
    .multiPart("username", "john")
    .multiPart("email", "john@example.com")
.when()
    .post("/register")
.then()
    .statusCode(200);
```

---

## 24. JSON Path

```java
Response response = given()
    .when()
        .get("/posts/1")
    .then()
        .extract().response();

JsonPath jsonPath = response.jsonPath();
String title = jsonPath.getString("title");
int userId = jsonPath.getInt("userId");
```

---

## 25. Filters

```java
// Log filter
given()
    .filter(new RequestLoggingFilter())
    .filter(new ResponseLoggingFilter())
.when()
    .get("/posts/1")
.then()
    .statusCode(200);
```

---

## Common Imports

```java
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
```

---

## Tips

1. **Use BDD Style**: Given-When-Then makes tests readable
2. **Log Everything Initially**: Use `.log().all()` when learning
3. **Extract Specs**: Reuse RequestSpecification and ResponseSpecification
4. **Use POJOs**: Better than String/HashMap for complex objects
5. **Validate Schemas**: Ensure API contract compliance
6. **Handle Failures Gracefully**: APIs may be down or rate-limited
7. **Keep Tests Independent**: Each test should work standalone
8. **Use Meaningful Assertions**: Validate what matters
9. **Comment Your Code**: Help others (and future you) understand
10. **Practice Daily**: Consistency is key to mastery

---

**Happy Testing! 🚀**
