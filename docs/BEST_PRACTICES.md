# REST Assured Best Practices Guide

A comprehensive guide to writing clean, maintainable, and effective API tests using REST Assured.

---

## Table of Contents

- [Test Organization](#test-organization)
- [Code Structure](#code-structure)
- [Request/Response Handling](#requestresponse-handling)
- [Assertions and Validations](#assertions-and-validations)
- [Data Management](#data-management)
- [Error Handling](#error-handling)
- [Performance Testing](#performance-testing)
- [Security Testing](#security-testing)
- [Maintenance and Debugging](#maintenance-and-debugging)
- [Common Anti-Patterns](#common-anti-patterns)

---

## Test Organization

### 1. Follow Clear Naming Conventions

#### ✅ Good Test Names

```java
@Test
public void testGetUser_WithValidId_ReturnsUser200() { }

@Test
public void testCreateUser_WithValidData_Returns201() { }

@Test
public void testDeleteUser_WithInvalidId_Returns404() { }
```

#### ❌ Bad Test Names

```java
@Test
public void test1() { }

@Test
public void testAPI() { }

@Test
public void doTest() { }
```

### 2. Group Related Tests

```java
public class UserApiTest {
    
    @Test(groups = "smoke")
    public void testGetAllUsers() { }
    
    @Test(groups = "regression")
    public void testGetUserById() { }
    
    @Test(groups = {"smoke", "critical"})
    public void testCreateUser() { }
}
```

### 3. Use TestNG Annotations Effectively

```java
public class BaseTest {
    
    @BeforeClass
    public void setUp() {
        // Initialize common resources
    }
    
    @BeforeMethod
    public void beforeEachTest() {
        // Run before each test
    }
    
    @AfterMethod
    public void afterEachTest() {
        // Cleanup after each test
    }
    
    @AfterClass
    public void tearDown() {
        // Final cleanup
    }
}
```

---

## Code Structure

### 1. Use Request and Response Specifications

#### ✅ Good: Reusable Specifications

```java
RequestSpecification requestSpec = new RequestSpecBuilder()
    .setBaseUri("https://api.example.com")
    .setContentType(ContentType.JSON)
    .addHeader("Authorization", "Bearer token")
    .build();

given()
    .spec(requestSpec)
.when()
    .get("/users");
```

#### ❌ Bad: Repeated Code

```java
given()
    .baseUri("https://api.example.com")
    .contentType(ContentType.JSON)
    .header("Authorization", "Bearer token")
.when()
    .get("/users");

// Same code repeated in every test...
```

### 2. Keep Tests Independent

#### ✅ Good: Independent Tests

```java
@Test
public void testCreateUser() {
    // Create user
    String userId = createUser();
    
    // Test and cleanup
    validateUser(userId);
    deleteUser(userId);
}

@Test
public void testUpdateUser() {
    // Create own user
    String userId = createUser();
    
    // Test and cleanup
    updateUser(userId);
    deleteUser(userId);
}
```

#### ❌ Bad: Dependent Tests

```java
private static String userId;

@Test(priority = 1)
public void testCreateUser() {
    userId = createUser(); // Stores in class variable
}

@Test(priority = 2, dependsOnMethods = "testCreateUser")
public void testUpdateUser() {
    updateUser(userId); // Depends on previous test
}
```

### 3. Use Page Object Model Pattern (For API)

```java
// ApiEndpoints.java
public class UserApiEndpoints {
    private static final String BASE_PATH = "/users";
    
    public static Response getAllUsers() {
        return given()
            .spec(ApiConfig.getRequestSpec())
        .when()
            .get(BASE_PATH);
    }
    
    public static Response getUserById(int id) {
        return given()
            .spec(ApiConfig.getRequestSpec())
        .when()
            .get(BASE_PATH + "/" + id);
    }
    
    public static Response createUser(User user) {
        return given()
            .spec(ApiConfig.getRequestSpec())
            .body(user)
        .when()
            .post(BASE_PATH);
    }
}

// Test class
@Test
public void testGetUser() {
    Response response = UserApiEndpoints.getUserById(1);
    response.then().statusCode(200);
}
```

---

## Request/Response Handling

### 1. Use POJOs for Serialization/Deserialization

#### ✅ Good: Type-Safe with POJOs

```java
User user = User.builder()
    .name("John Doe")
    .email("john@example.com")
    .build();

User createdUser = given()
    .body(user)
.when()
    .post("/users")
.then()
    .statusCode(201)
    .extract()
    .as(User.class);

assertEquals(user.getName(), createdUser.getName());
```

#### ❌ Bad: String Manipulation

```java
String json = "{\"name\":\"John Doe\",\"email\":\"john@example.com\"}";

given()
    .body(json)
.when()
    .post("/users");
```

### 2. Log Requests and Responses Appropriately

#### ✅ Good: Conditional Logging

```java
// For debugging
given()
    .log().all()  // Log everything during development
.when()
    .get("/users");

// For production
given()
    .log().ifValidationFails()  // Log only on failure
.when()
    .get("/users");
```

#### ❌ Bad: No Logging

```java
given()
.when()
    .get("/users");
// Can't debug when it fails
```

### 3. Extract and Reuse Response Data

#### ✅ Good: Extract and Reuse

```java
Response response = given()
    .get("/users/1")
.then()
    .statusCode(200)
    .extract()
    .response();

int userId = response.path("id");
String userName = response.path("name");

// Use extracted data in next request
given()
    .pathParam("userId", userId)
.when()
    .get("/posts/{userId}");
```

---

## Assertions and Validations

### 1. Validate Multiple Aspects

#### ✅ Good: Comprehensive Validation

```java
given()
    .get("/users/1")
.then()
    .statusCode(200)
    .contentType(ContentType.JSON)
    .time(lessThan(2000L))
    .header("Content-Length", notNullValue())
    .body("id", equalTo(1))
    .body("name", notNullValue())
    .body("email", containsString("@"));
```

#### ❌ Bad: Minimal Validation

```java
given()
    .get("/users/1")
.then()
    .statusCode(200);
// Missing validations
```

### 2. Use Appropriate Matchers

```java
import static org.hamcrest.Matchers.*;

// Equality
.body("id", equalTo(1))
.body("name", is("John"))

// Null checks
.body("email", notNullValue())
.body("middleName", nullValue())

// String matching
.body("email", containsString("@"))
.body("url", startsWith("https://"))
.body("name", endsWith("Doe"))

// Collections
.body("items", hasSize(5))
.body("items", not(empty()))
.body("items", hasItem("value"))

// Numeric
.body("age", greaterThan(18))
.body("price", lessThanOrEqualTo(100))
.body("count", both(greaterThan(0)).and(lessThan(10)))
```

### 3. Validate Response Schema

```java
@Test
public void testUserSchemaValidation() {
    given()
        .get("/users/1")
    .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
}
```

---

## Data Management

### 1. Use Test Data Generators

#### ✅ Good: Dynamic Data

```java
@Test
public void testCreateUser() {
    User user = TestDataGenerator.generateRandomUser();
    
    given()
        .body(user)
    .when()
        .post("/users")
    .then()
        .statusCode(201)
        .body("email", equalTo(user.getEmail()));
}
```

#### ❌ Bad: Hardcoded Data

```java
@Test
public void testCreateUser() {
    String json = "{\"name\":\"John\",\"email\":\"john@test.com\"}";
    // Will fail if user already exists
}
```

### 2. Manage Test Data Lifecycle

```java
public class UserTest {
    private List<String> createdUserIds = new ArrayList<>();
    
    @Test
    public void testUser() {
        // Create test data
        String userId = createUser();
        createdUserIds.add(userId);
        
        // Test logic
        validateUser(userId);
    }
    
    @AfterMethod
    public void cleanup() {
        // Clean up all created users
        for (String id : createdUserIds) {
            deleteUser(id);
        }
        createdUserIds.clear();
    }
}
```

### 3. Use Data Providers for Data-Driven Tests

```java
@DataProvider(name = "validUserData")
public Object[][] validUserData() {
    return new Object[][] {
        {"John Doe", "john@example.com"},
        {"Jane Smith", "jane@example.com"},
        {"Bob Johnson", "bob@example.com"}
    };
}

@Test(dataProvider = "validUserData")
public void testCreateUser(String name, String email) {
    User user = User.builder()
        .name(name)
        .email(email)
        .build();
        
    given()
        .body(user)
    .when()
        .post("/users")
    .then()
        .statusCode(201);
}
```

---

## Error Handling

### 1. Test Negative Scenarios

```java
@Test
public void testGetUser_WithInvalidId_Returns404() {
    given()
        .get("/users/99999")
    .then()
        .statusCode(404)
        .body("error", notNullValue());
}

@Test
public void testCreateUser_WithInvalidData_Returns400() {
    String invalidJson = "{\"email\":\"invalid-email\"}";
    
    given()
        .body(invalidJson)
    .when()
        .post("/users")
    .then()
        .statusCode(400)
        .body("message", containsString("validation"));
}

@Test
public void testUnauthorizedAccess_Returns401() {
    given()
        // No auth header
    .when()
        .get("/protected/resource")
    .then()
        .statusCode(401);
}
```

### 2. Handle Flaky Tests

```java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void testExternalApiCall() {
    // Test that might fail due to network issues
}

// RetryAnalyzer.java
public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 3;
    
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
```

### 3. Use Try-Catch for Expected Exceptions

```java
@Test
public void testInvalidJsonHandling() {
    try {
        given()
            .body("invalid json")
        .when()
            .post("/users");
        fail("Should have thrown exception");
    } catch (Exception e) {
        assertTrue(e.getMessage().contains("JSON"));
    }
}
```

---

## Performance Testing

### 1. Validate Response Time

```java
@Test
public void testResponseTime() {
    given()
        .get("/users")
    .then()
        .time(lessThan(2000L))  // Less than 2 seconds
        .time(greaterThan(100L)); // But not suspiciously fast
}
```

### 2. Measure and Report Performance

```java
@Test
public void testApiPerformance() {
    long startTime = System.currentTimeMillis();
    
    Response response = given()
        .get("/users")
    .then()
        .statusCode(200)
        .extract()
        .response();
    
    long endTime = System.currentTimeMillis();
    long responseTime = response.time();
    
    System.out.println("Total time: " + (endTime - startTime) + "ms");
    System.out.println("Response time: " + responseTime + "ms");
    
    assertTrue(responseTime < 2000, "Response time exceeded 2 seconds");
}
```

### 3. Load Testing Pattern

```java
@Test(threadPoolSize = 10, invocationCount = 100, timeOut = 10000)
public void testConcurrentRequests() {
    given()
        .get("/users")
    .then()
        .statusCode(200);
}
```

---

## Security Testing

### 1. Test Authentication

```java
@Test
public void testWithoutAuth_Returns401() {
    given()
    .when()
        .get("/protected/resource")
    .then()
        .statusCode(401);
}

@Test
public void testWithInvalidToken_Returns401() {
    given()
        .header("Authorization", "Bearer invalid_token")
    .when()
        .get("/protected/resource")
    .then()
        .statusCode(401);
}

@Test
public void testWithValidToken_Returns200() {
    given()
        .header("Authorization", "Bearer " + getValidToken())
    .when()
        .get("/protected/resource")
    .then()
        .statusCode(200);
}
```

### 2. Test Input Validation

```java
@Test
public void testSQLInjection_IsBlocked() {
    String sqlInjection = "1' OR '1'='1";
    
    given()
        .queryParam("id", sqlInjection)
    .when()
        .get("/users")
    .then()
        .statusCode(400); // Should be rejected
}

@Test
public void testXSSAttack_IsEscaped() {
    String xssPayload = "<script>alert('XSS')</script>";
    
    User user = User.builder()
        .name(xssPayload)
        .build();
    
    String returnedName = given()
        .body(user)
    .when()
        .post("/users")
    .then()
        .statusCode(201)
        .extract()
        .path("name");
    
    assertFalse(returnedName.contains("<script>"));
}
```

---

## Maintenance and Debugging

### 1. Use Descriptive Error Messages

```java
@Test
public void testUser() {
    Response response = given().get("/users/1");
    
    int actualStatusCode = response.statusCode();
    assertEquals(200, actualStatusCode, 
        "Expected status code 200 but got " + actualStatusCode);
    
    String name = response.path("name");
    assertNotNull(name, "User name should not be null");
}
```

### 2. Enable Detailed Logging for Debugging

```java
RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

@Test
public void testWithAutoLogging() {
    given()
    .when()
        .get("/users/1")
    .then()
        .statusCode(200);
    // Logs automatically if test fails
}
```

### 3. Use Filters for Custom Logging

```java
@Test
public void testWithCustomFilter() {
    given()
        .filter(new AllureRestAssured())  // Allure reporting
        .filter(new RequestLoggingFilter())
        .filter(new ResponseLoggingFilter())
    .when()
        .get("/users");
}
```

---

## Common Anti-Patterns

### ❌ 1. Testing Too Much in One Test

```java
// Bad - Does too many things
@Test
public void testEverything() {
    createUser();
    updateUser();
    deleteUser();
    getAllUsers();
    // Too much in one test
}
```

```java
// Good - Separate concerns
@Test
public void testCreateUser() { }

@Test
public void testUpdateUser() { }

@Test
public void testDeleteUser() { }
```

### ❌ 2. Ignoring Status Codes

```java
// Bad
given()
    .get("/users");
// No validation!
```

```java
// Good
given()
    .get("/users")
.then()
    .statusCode(200);
```

### ❌ 3. Not Testing Edge Cases

```java
// Bad - Only happy path
@Test
public void testGetUser() {
    given().get("/users/1").then().statusCode(200);
}
```

```java
// Good - Test edge cases
@Test
public void testGetUser_ValidId() { }

@Test
public void testGetUser_InvalidId() { }

@Test
public void testGetUser_NonExistentId() { }

@Test
public void testGetUser_NegativeId() { }
```

### ❌ 4. Hardcoding URLs and Credentials

```java
// Bad
.baseUri("https://prod-api.example.com")
.header("API-Key", "abc123xyz")
```

```java
// Good
.baseUri(ConfigReader.getProperty("base.uri"))
.header("API-Key", ConfigReader.getProperty("api.key"))
```

---

## Summary Checklist

Use this checklist for every test:

- [ ] Test has a clear, descriptive name
- [ ] Uses Given-When-Then pattern
- [ ] Validates status code
- [ ] Validates response body
- [ ] Validates response time
- [ ] Uses request/response specifications
- [ ] Uses POJOs where appropriate
- [ ] Handles test data cleanup
- [ ] Independent from other tests
- [ ] Tests both positive and negative scenarios
- [ ] Has appropriate logging
- [ ] Follows coding standards

---

## Additional Resources

- [REST Assured Documentation](https://rest-assured.io/)
- [TestNG Best Practices](https://testng.org/doc/documentation-main.html)
- [API Testing Guide](GETTING_STARTED.md)
- [API Reference](API_REFERENCE.md)

---

**Remember**: Good tests are readable, maintainable, reliable, and fast!

Happy Testing! 🚀
