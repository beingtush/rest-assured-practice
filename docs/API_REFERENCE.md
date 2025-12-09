# API Reference Guide

Complete reference for the REST Assured API Testing Framework utilities and helpers.

---

## Table of Contents

- [Utility Classes](#utility-classes)
  - [ApiConfig](#apiconfig)
  - [TestDataGenerator](#testdatagenerator)
  - [JsonUtils](#jsonutils)
  - [ConfigReader](#configreader)
- [Model Classes](#model-classes)
- [Test Examples](#test-examples)

---

## Utility Classes

### ApiConfig

**Package**: `com.restassured.practice.utils`

Centralized configuration for REST Assured request and response specifications.

#### Methods

##### Request Specifications

```java
// Get default request specification with base URI
RequestSpecification getDefaultRequestSpec(String baseUri)

// Pre-configured request specifications for popular APIs
RequestSpecification getJsonPlaceholderRequestSpec()
RequestSpecification getReqResRequestSpec()
RequestSpecification getHttpBinRequestSpec()
RequestSpecification getRestCountriesRequestSpec()
```

**Example Usage:**
```java
given()
    .spec(ApiConfig.getJsonPlaceholderRequestSpec())
.when()
    .get("/users/1")
.then()
    .statusCode(200);
```

##### Response Specifications

```java
// Default response specification (validates response time < 5s)
ResponseSpecification getDefaultResponseSpec()

// Success response specification (200 OK, response time < 3s)
ResponseSpecification getSuccessResponseSpec()

// Created response specification (201 Created, response time < 3s)
ResponseSpecification getCreatedResponseSpec()
```

**Example Usage:**
```java
given()
    .spec(ApiConfig.getJsonPlaceholderRequestSpec())
.when()
    .get("/users")
.then()
    .spec(ApiConfig.getSuccessResponseSpec())
    .body("size()", greaterThan(0));
```

---

### TestDataGenerator

**Package**: `com.restassured.practice.utils`

Utility class for generating random test data.

#### Methods

```java
// Generate a random User object
User generateRandomUser()

// Generate a random Post object
Post generateRandomPost()

// Generate a random email address
String generateRandomEmail()

// Generate a random string of specified length
String generateRandomString(int length)

// Generate a random integer between min and max (inclusive)
int generateRandomInt(int min, int max)
```

**Example Usage:**
```java
// Generate random user
User randomUser = TestDataGenerator.generateRandomUser();

// Generate random email
String email = TestDataGenerator.generateRandomEmail();

// Generate random string
String randomName = TestDataGenerator.generateRandomString(10);

// Generate random number
int randomId = TestDataGenerator.generateRandomInt(1, 100);
```

**Use Cases:**
- Data-driven testing with unique data
- Avoiding test data conflicts
- Stress testing with varied inputs
- Negative testing with random invalid data

---

### JsonUtils

**Package**: `com.restassured.practice.utils`

Utility class for JSON serialization, deserialization, and file operations.

#### Methods

```java
// Convert object to JSON string
String toJson(Object object)

// Convert JSON string to object
<T> T fromJson(String json, Class<T> clazz)

// Read JSON from file
<T> T readFromFile(String filePath, Class<T> clazz)

// Write object to JSON file
void writeToFile(Object object, String filePath)

// Pretty print JSON string
String prettyPrint(String json)
```

**Example Usage:**

```java
// Object to JSON
User user = User.builder().name("John").email("john@test.com").build();
String json = JsonUtils.toJson(user);

// JSON to Object
User deserializedUser = JsonUtils.fromJson(json, User.class);

// Read from file
User userFromFile = JsonUtils.readFromFile("testdata/user.json", User.class);

// Write to file
JsonUtils.writeToFile(user, "output/user.json");

// Pretty print
String prettyJson = JsonUtils.prettyPrint(rawJson);
System.out.println(prettyJson);
```

---

### ConfigReader

**Package**: `com.restassured.practice.utils`

Utility class for reading configuration properties.

#### Methods

```java
// Get property value by key
String getProperty(String key)

// Get property with default value
String getProperty(String key, String defaultValue)
```

**Example Usage:**
```java
String baseUrl = ConfigReader.getProperty("base.url");
String timeout = ConfigReader.getProperty("timeout", "5000");
```

---

## Model Classes

### User

**Package**: `com.restassured.practice.models`

POJO class representing a user with Lombok annotations.

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}
```

**Usage:**
```java
User user = User.builder()
    .name("John Doe")
    .email("john@example.com")
    .username("johndoe")
    .build();
```

---

### Post

**Package**: `com.restassured.practice.models`

POJO class representing a blog post.

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
```

**Usage:**
```java
Post post = Post.builder()
    .userId(1)
    .title("Test Post")
    .body("Post content")
    .build();
```

---

### ReqResUser

**Package**: `com.restassured.practice.models`

POJO class for ReqRes API user structure.

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqResUser {
    private Integer id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String avatar;
}
```

---

## Test Examples

### Using Request/Response Specifications

```java
@Test
public void testWithSpecs() {
    given()
        .spec(ApiConfig.getJsonPlaceholderRequestSpec())
    .when()
        .get("/users/1")
    .then()
        .spec(ApiConfig.getSuccessResponseSpec())
        .body("id", equalTo(1));
}
```

### Data-Driven Testing

```java
@DataProvider(name = "userIds")
public Object[][] userIds() {
    return new Object[][] {{1}, {2}, {3}};
}

@Test(dataProvider = "userIds")
public void testMultipleUsers(int userId) {
    given()
        .spec(ApiConfig.getJsonPlaceholderRequestSpec())
    .when()
        .get("/users/" + userId)
    .then()
        .statusCode(200)
        .body("id", equalTo(userId));
}
```

### Using Test Data Generator

```java
@Test
public void testWithRandomData() {
    User randomUser = TestDataGenerator.generateRandomUser();
    
    given()
        .spec(ApiConfig.getJsonPlaceholderRequestSpec())
        .body(randomUser)
    .when()
        .post("/users")
    .then()
        .statusCode(201)
        .body("name", equalTo(randomUser.getName()));
}
```

### Request Chaining

```java
@Test
public void testChainedRequests() {
    // First request - create resource
    String id = given()
        .spec(ApiConfig.getJsonPlaceholderRequestSpec())
        .body(post)
    .when()
        .post("/posts")
    .then()
        .statusCode(201)
        .extract()
        .path("id")
        .toString();
    
    // Second request - get created resource
    given()
        .spec(ApiConfig.getJsonPlaceholderRequestSpec())
    .when()
        .get("/posts/" + id)
    .then()
        .statusCode(200)
        .body("id", equalTo(Integer.parseInt(id)));
}
```

---

## Best Practices

### 1. Use Specifications for Reusability

```java
// ✅ Good - Reusable
RequestSpecification spec = ApiConfig.getJsonPlaceholderRequestSpec();

// ❌ Bad - Repetitive
given()
    .baseUri("https://jsonplaceholder.typicode.com")
    .contentType(ContentType.JSON)
```

### 2. Use POJOs for Type Safety

```java
// ✅ Good - Type safe
User user = response.as(User.class);

// ❌ Bad - String manipulation
String name = response.path("name");
```

### 3. Use Test Data Generators

```java
// ✅ Good - Unique data
User user = TestDataGenerator.generateRandomUser();

// ❌ Bad - Hardcoded data
User user = new User("John", "john@test.com");
```

### 4. Extract Common Validations

```java
// ✅ Good - Reusable response spec
.then()
    .spec(ApiConfig.getSuccessResponseSpec())

// ❌ Bad - Repeated validations
.then()
    .statusCode(200)
    .contentType(ContentType.JSON)
    .time(lessThan(3000L))
```

---

## Advanced Patterns

### Building Complex Request Bodies

```java
Map<String, Object> requestBody = new HashMap<>();
requestBody.put("name", "John");
requestBody.put("email", TestDataGenerator.generateRandomEmail());
requestBody.put("age", TestDataGenerator.generateRandomInt(18, 65));

given()
    .spec(ApiConfig.getJsonPlaceholderRequestSpec())
    .body(requestBody)
.when()
    .post("/users");
```

### Response Extraction and Reuse

```java
Response response = given()
    .spec(ApiConfig.getJsonPlaceholderRequestSpec())
.when()
    .get("/users/1")
.then()
    .extract()
    .response();

// Extract different data types
int id = response.path("id");
String name = response.path("name");
List<String> emails = response.path("email");
```

### Conditional Testing

```java
Response response = given()
    .spec(ApiConfig.getJsonPlaceholderRequestSpec())
.when()
    .get("/users")
.then()
    .extract()
    .response();

if (response.statusCode() == 200) {
    // Perform additional validations
    List<Integer> ids = response.path("id");
    assertTrue(ids.size() > 0);
}
```

---

## Configuration

### Adding New API Specifications

To add a new API specification, update `ApiConfig.java`:

```java
public static RequestSpecification getMyApiRequestSpec() {
    return new RequestSpecBuilder()
            .setBaseUri("https://myapi.com")
            .setContentType(ContentType.JSON)
            .addHeader("API-Key", "your-key")
            .log(LogDetail.ALL)
            .build();
}
```

---

## Troubleshooting

### Common Issues

**Issue**: `NullPointerException` when deserializing JSON

**Solution**: Ensure POJO has `@NoArgsConstructor` and matching field names

**Issue**: Request spec not being applied

**Solution**: Use `.spec()` method, not `.given()`

**Issue**: Response time validation failing

**Solution**: Check network connectivity or increase timeout in response spec

---

For more examples, check the test classes in `src/test/java/com/restassured/practice/tests/`
