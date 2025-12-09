# Getting Started Guide

Welcome to the REST Assured API Testing Framework! This guide will help you get started with API testing using REST Assured.

---

## Table of Contents

- [What is API Testing?](#what-is-api-testing)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Setup](#project-setup)
- [Your First Test](#your-first-test)
- [Understanding REST Assured Syntax](#understanding-rest-assured-syntax)
- [Running Tests](#running-tests)
- [Next Steps](#next-steps)

---

## What is API Testing?

API (Application Programming Interface) testing is a type of software testing that validates APIs directly. It focuses on:

- **Functionality**: Does the API do what it's supposed to do?
- **Reliability**: Does it work consistently?
- **Performance**: How fast does it respond?
- **Security**: Is it secure?

### Why REST Assured?

REST Assured is a Java library that makes testing REST APIs simple and intuitive. It provides:

- ✅ Easy-to-read, BDD-style syntax
- ✅ Powerful validation capabilities
- ✅ JSON/XML parsing
- ✅ Authentication support
- ✅ Schema validation
- ✅ Excellent integration with TestNG/JUnit

---

## Prerequisites

Before starting, ensure you have:

### Required Software

1. **Java JDK 11 or higher**
   - Download: https://www.oracle.com/java/technologies/downloads/
   - Verify: `java -version`

2. **Apache Maven 3.6+**
   - Download: https://maven.apache.org/download.cgi
   - Verify: `mvn -version`

3. **Git** (for cloning the repository)
   - Download: https://git-scm.com/downloads
   - Verify: `git --version`

### Recommended Software

- **IDE**: IntelliJ IDEA (Community or Ultimate), Eclipse, or VS Code
- **Postman** (optional, for manual API testing)
- **Allure** (for report generation)

### Knowledge Prerequisites

- Basic Java programming
- Understanding of HTTP methods (GET, POST, PUT, DELETE)
- Basic JSON/XML knowledge
- Familiarity with Maven projects

---

## Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/beingtush/rest-assured-practice.git
cd rest-assured-practice
```

### Step 2: Import Project in IDE

#### IntelliJ IDEA
1. Open IntelliJ IDEA
2. File → Open → Select `pom.xml`
3. Open as Project
4. Wait for Maven to download dependencies

#### Eclipse
1. Open Eclipse
2. File → Import → Maven → Existing Maven Projects
3. Browse to project folder
4. Finish

#### VS Code
1. Open VS Code
2. File → Open Folder → Select project folder
3. Install "Extension Pack for Java" if prompted
4. Maven will auto-detect the project

### Step 3: Verify Setup

Run the verification script:

```bash
# Windows
powershell .\verify-setup.ps1

# Linux/Mac
chmod +x verify-setup.sh
./verify-setup.sh
```

Or manually verify:

```bash
mvn clean install
```

If successful, you should see:
```
[INFO] BUILD SUCCESS
```

---

## Project Setup

### Understanding the Project Structure

```
rest-assured-practice/
├── src/
│   ├── main/java/
│   │   └── com/restassured/practice/
│   │       ├── models/           # POJO classes
│   │       └── utils/            # Helper utilities
│   └── test/java/
│       └── com/restassured/practice/tests/  # Test classes
├── pom.xml                       # Maven dependencies
└── testng.xml                    # TestNG configuration
```

### Key Files

- **pom.xml**: Maven configuration with all dependencies
- **testng.xml**: TestNG suite configuration
- **src/test/java/**: All test classes
- **src/main/java/**: Utility classes and POJOs

---

## Your First Test

Let's create your first API test!

### Step 1: Create a Test Class

Create a new file: `src/test/java/com/restassured/practice/tests/MyFirstTest.java`

```java
package com.restassured.practice.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MyFirstTest {

    @Test
    public void testGetAllUsers() {
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }
}
```

### Step 2: Run the Test

#### From IDE
- Right-click on the test method → Run 'testGetAllUsers()'

#### From Command Line
```bash
mvn test -Dtest=MyFirstTest
```

### Step 3: Verify Results

You should see:
- ✅ Test passed
- Response logged in console
- Test execution time

---

## Understanding REST Assured Syntax

REST Assured uses a **BDD (Behavior-Driven Development)** style syntax:

### The Given-When-Then Pattern

```java
given()           // Preconditions (headers, auth, params)
    .header("Content-Type", "application/json")
    .queryParam("page", 1)
.when()           // Action (HTTP method and endpoint)
    .get("/users")
.then()           // Assertions (validate response)
    .statusCode(200)
    .body("data[0].id", equalTo(1));
```

### Breaking It Down

#### 1. Given (Setup)

```java
given()
    .baseUri("https://api.example.com")          // Base URL
    .header("Authorization", "Bearer token")     // Headers
    .queryParam("page", 1)                       // Query params
    .pathParam("id", 5)                          // Path params
    .body(requestBody)                           // Request body
```

#### 2. When (Action)

```java
.when()
    .get("/endpoint")           // GET request
    .post("/endpoint")          // POST request
    .put("/endpoint/{id}")      // PUT request
    .patch("/endpoint/{id}")    // PATCH request
    .delete("/endpoint/{id}")   // DELETE request
```

#### 3. Then (Validation)

```java
.then()
    .statusCode(200)                           // Status code
    .contentType(ContentType.JSON)             // Content type
    .body("name", equalTo("John"))             // Field validation
    .body("items.size()", greaterThan(0))      // Size validation
    .time(lessThan(2000L))                     // Response time
```

---

## Common API Testing Scenarios

### 1. Simple GET Request

```java
@Test
public void testSimpleGet() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
    .when()
        .get("/users/1")
    .then()
        .statusCode(200)
        .body("id", equalTo(1))
        .body("name", notNullValue());
}
```

### 2. POST Request with Body

```java
@Test
public void testCreateUser() {
    String requestBody = """
        {
            "name": "John Doe",
            "email": "john@example.com"
        }
        """;

    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .contentType(ContentType.JSON)
        .body(requestBody)
    .when()
        .post("/users")
    .then()
        .statusCode(201)
        .body("name", equalTo("John Doe"));
}
```

### 3. GET with Query Parameters

```java
@Test
public void testWithQueryParams() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .queryParam("userId", 1)
    .when()
        .get("/posts")
    .then()
        .statusCode(200)
        .body("[0].userId", equalTo(1));
}
```

### 4. PUT Request (Update)

```java
@Test
public void testUpdateUser() {
    String updateBody = """
        {
            "name": "Jane Doe",
            "email": "jane@example.com"
        }
        """;

    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .contentType(ContentType.JSON)
        .body(updateBody)
    .when()
        .put("/users/1")
    .then()
        .statusCode(200)
        .body("name", equalTo("Jane Doe"));
}
```

### 5. DELETE Request

```java
@Test
public void testDeleteUser() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
    .when()
        .delete("/users/1")
    .then()
        .statusCode(200);
}
```

---

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=BasicGetRequestTest
```

### Run Specific Test Method

```bash
mvn test -Dtest=BasicGetRequestTest#testGetAllUsers
```

### Run from TestNG Suite

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run with Allure Report

```bash
mvn clean test
mvn allure:serve
```

---

## Next Steps

Now that you've created your first test, explore more:

### 1. Study Existing Tests

Start with these test classes (in order):
1. `BasicGetRequestTest.java` - Simple GET requests
2. `PostRequestTest.java` - POST requests
3. `QueryParametersTest.java` - Query parameters
4. `PathParametersTest.java` - Path parameters
5. `AuthenticationTest.java` - Authentication examples

### 2. Learn Advanced Features

- Request/Response specifications (`AdvancedRequestSpecTest.java`)
- Data-driven testing (`DataDrivenTest.java`)
- Serialization/Deserialization (`SerializationDeserializationTest.java`)
- JSON Schema Validation (`JsonSchemaValidationTest.java`)

### 3. Practice with Real APIs

Try testing these public APIs:
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
- [ReqRes](https://reqres.in/)
- [HTTPBin](https://httpbin.org/)
- [REST Countries](https://restcountries.com/)

### 4. Read Documentation

- [REST Assured Docs](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/doc/)
- [API Reference Guide](API_REFERENCE.md)
- [Best Practices](BEST_PRACTICES.md)

### 5. Create Your Own Tests

- Pick an API you use
- Write tests for its endpoints
- Practice different scenarios
- Share your tests!

---

## Common Mistakes to Avoid

### ❌ Hardcoding URLs

```java
// Bad
.baseUri("https://api.example.com")
```

```java
// Good
.spec(ApiConfig.getMyApiRequestSpec())
```

### ❌ Not Validating Response

```java
// Bad
.when().get("/users");
```

```java
// Good
.when().get("/users")
.then().statusCode(200);
```

### ❌ Ignoring Response Time

```java
// Bad
.then().statusCode(200);
```

```java
// Good
.then()
    .statusCode(200)
    .time(lessThan(2000L));
```

---

## Getting Help

If you encounter issues:

1. **Check Documentation**: Review existing test examples
2. **Search Issues**: Look for similar problems on GitHub
3. **Ask Questions**: Open a new issue with details
4. **Community**: Join REST Assured forums/communities

---

## Summary

You've learned:
- ✅ How to set up the project
- ✅ REST Assured syntax (Given-When-Then)
- ✅ How to write your first test
- ✅ Common testing scenarios
- ✅ How to run tests

**Next**: Try modifying existing tests or create new ones!

---

**Happy Testing! 🚀**
