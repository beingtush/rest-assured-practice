# REST Assured API Testing Framework 🚀

[![Java](https://img.shields.io/badge/Java-11%2B-orange?style=flat&logo=java)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue?style=flat&logo=apache-maven)](https://maven.apache.org/)
[![REST Assured](https://img.shields.io/badge/REST%20Assured-5.3.0-green?style=flat)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.7.1-red?style=flat)](https://testng.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat)](LICENSE)
[![GitHub Stars](https://img.shields.io/github/stars/beingtush/rest-assured-practice?style=social)](https://github.com/beingtush/rest-assured-practice/stargazers)

> **A comprehensive, production-ready REST Assured API testing framework** demonstrating modern API test automation practices with Java, Maven, TestNG, and Allure reporting. Perfect for learning API testing or building your own test automation framework.

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Prerequisites](#-prerequisites)
- [Quick Start](#-quick-start)
- [Project Structure](#-project-structure)
- [Topics Covered](#-topics-covered)
- [API Testing Examples](#-api-testing-examples)
- [Running Tests](#-running-tests)
- [Reporting](#-reporting)
- [Best Practices](#-best-practices)
- [Public APIs Used](#-public-apis-used)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)

---

## 🎯 Overview

This **REST Assured API Testing Framework** is a complete guide to API test automation, covering everything from basic GET requests to advanced authentication, file uploads, and JSON schema validation. Built with industry best practices, this framework is ideal for:

- 🎓 **Learning API Testing** - Comprehensive examples for beginners and advanced users
- 🏢 **Interview Preparation** - Common API testing scenarios and patterns
- 🛠️ **Framework Development** - Starter template for building production frameworks
- 📚 **Reference Guide** - Quick examples for REST Assured syntax and features

### Why This Framework?

✅ **30+ Test Cases** covering all HTTP methods and scenarios  
✅ **Real-World Examples** using popular public APIs  
✅ **Production-Ready** code with proper structure and organization  
✅ **BDD-Style Tests** using Given-When-Then pattern  
✅ **Allure Reporting** for beautiful test reports  
✅ **JSON Schema Validation** for contract testing  
✅ **POJO Serialization** for type-safe API testing  

---

## ✨ Features

- **Complete HTTP Method Coverage**: GET, POST, PUT, PATCH, DELETE
- **Authentication Examples**: Basic Auth, Bearer Token, API Key
- **Request Handling**: Headers, Cookies, Query Parameters, Path Parameters
- **Response Validation**: Status codes, JSON/XML assertions, Schema validation
- **Data Handling**: Serialization/Deserialization with POJOs
- **File Operations**: Upload (multipart) and Download
- **Reporting**: Allure reports with detailed test execution logs
- **Modular Design**: Reusable models and utilities
- **TestNG Integration**: Test suites, groups, and parallel execution

---

## 📦 Prerequisites

Before running this project, ensure you have:

- ☕ **Java 11 or higher** - [Download JDK](https://www.oracle.com/java/technologies/downloads/)
- 📦 **Maven 3.6+** - [Install Maven](https://maven.apache.org/install.html)
- 🌐 **Internet Connection** - Required for API calls to public endpoints
- 💻 **IDE** (Optional) - IntelliJ IDEA, Eclipse, or VS Code with Java extensions

### Verify Installation

```bash
java -version
mvn -version
```

---

## 🚀 Quick Start

Get up and running in 3 simple steps:

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/beingtush/rest-assured-practice.git
cd rest-assured-practice
```

### 2️⃣ Install Dependencies

```bash
mvn clean install
```

### 3️⃣ Run Tests

```bash
mvn test
```

That's it! Your tests should now be running. Check the `allure-results/` folder for test results.

---

## 📁 Project Structure

```
rest-assured-practice/
│
├── src/
│   ├── main/java/com/restassured/practice/
│   │   ├── models/                 # POJO classes for serialization
│   │   │   ├── Post.java
│   │   │   ├── User.java
│   │   │   └── ReqResUser.java
│   │   └── utils/                  # Helper utilities
│   │       └── ConfigReader.java
│   │
│   └── test/
│       ├── java/com/restassured/practice/tests/
│       │   ├── BasicGetRequestTest.java
│       │   ├── PostRequestTest.java
│       │   ├── PutPatchDeleteTest.java
│       │   ├── QueryParametersTest.java
│       │   ├── PathParametersTest.java
│       │   ├── HeadersAndCookiesTest.java
│       │   ├── AuthenticationTest.java
│       │   ├── JsonSchemaValidationTest.java
│       │   ├── SerializationDeserializationTest.java
│       │   └── FileUploadDownloadTest.java
│       │
│       └── resources/
│           ├── schemas/             # JSON schemas for validation
│           └── testdata/            # Test data files
│
├── pom.xml                          # Maven dependencies
├── testng.xml                       # TestNG suite configuration
└── README.md                        # Project documentation
```

---

## 📚 Topics Covered

### 1. **Basic GET Requests** 🔍
- Simple GET requests
- Response validation
- Status code verification
- Response time assertions
- JSON/XML parsing

### 2. **POST Requests** ➕
- Creating resources
- Request body with JSON, XML, and Form data
- Response validation
- Content-Type handling

### 3. **PUT, PATCH, DELETE** ✏️
- Full resource updates (PUT)
- Partial updates (PATCH)
- Resource deletion (DELETE)
- Idempotency testing

### 4. **Query Parameters** 🔎
- Single and multiple parameters
- Parameter encoding
- Special character handling

### 5. **Path Parameters** 🛤️
- Dynamic URL paths
- Path variable substitution
- RESTful resource access

### 6. **Headers & Cookies** 🍪
- Custom headers
- Cookie handling
- Content-Type negotiation
- Accept headers

### 7. **Authentication** 🔐
- Basic Authentication
- Bearer Token (OAuth)
- API Key authentication
- Custom auth headers

### 8. **JSON Schema Validation** ✔️
- Schema validation
- Structure verification
- Contract testing
- API versioning validation

### 9. **Serialization & Deserialization** 🔄
- POJO to JSON (Serialization)
- JSON to POJO (Deserialization)
- Complex object handling
- Type-safe API testing

### 10. **File Upload & Download** 📁
- Multipart form data
- File upload
- File download
- Binary data handling

---

## 🧪 API Testing Examples

### Simple GET Request

```java
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
```

### POST Request with POJO

```java
@Test
public void testCreateUser() {
    User user = User.builder()
        .name("John Doe")
        .email("john@example.com")
        .build();
    
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .contentType(ContentType.JSON)
        .body(user)
    .when()
        .post("/users")
    .then()
        .statusCode(201)
        .body("name", equalTo("John Doe"));
}
```

### JSON Schema Validation

```java
@Test
public void testUserSchemaValidation() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
    .when()
        .get("/users/1")
    .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
}
```

---

## ▶️ Running Tests

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

### Run from IDE

- **IntelliJ IDEA**: Right-click on test class/method → Run
- **Eclipse**: Right-click on test class/method → Run As → TestNG Test
- **VS Code**: Use Test Runner extension

---

## 📊 Reporting

This framework uses **Allure** for beautiful, detailed test reports.

### Generate Allure Report

```bash
# Install Allure (if not already installed)
# Windows: scoop install allure
# Mac: brew install allure

# Generate and open report
mvn allure:serve
```

### Report Features

- 📈 Test execution dashboard
- 🔍 Detailed test steps
- 📸 Request/Response logs
- ⏱️ Execution timeline
- 🏆 Pass/Fail statistics

---

## 🏆 Best Practices Demonstrated

This framework showcases industry best practices:

1. ✅ **Given-When-Then Pattern** - BDD-style readable tests
2. ✅ **Request/Response Specifications** - Reusable configurations
3. ✅ **Log Filters** - Detailed logging for debugging
4. ✅ **Hamcrest Matchers** - Fluent assertions
5. ✅ **JSON Path & XML Path** - Easy data extraction
6. ✅ **POJO Models** - Type-safe serialization/deserialization
7. ✅ **Schema Validation** - Contract testing
8. ✅ **TestNG Organization** - Proper test structure
9. ✅ **DRY Principle** - No code duplication
10. ✅ **Proper Exception Handling** - Robust error management

---

## 🌐 Public APIs Used

This framework uses free, publicly available APIs:

| API | Purpose | Documentation |
|-----|---------|---------------|
| [JSONPlaceholder](https://jsonplaceholder.typicode.com/) | Fake REST API for testing | [Docs](https://jsonplaceholder.typicode.com/guide/) |
| [ReqRes](https://reqres.in/) | REST API with real responses | [Docs](https://reqres.in/) |
| [HTTPBin](https://httpbin.org/) | HTTP request & response testing | [Docs](https://httpbin.org/) |
| [REST Countries](https://restcountries.com/) | Country information API | [Docs](https://restcountries.com/) |

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. 🍴 Fork the repository
2. 🌿 Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. 💾 Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. 📤 Push to the branch (`git push origin feature/AmazingFeature`)
5. 🔃 Open a Pull Request

### Ideas for Contribution

- Add more test scenarios
- Improve documentation
- Add CI/CD pipeline
- Add more public API examples
- Improve error handling

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📬 Contact

**Tushar Raj**

- 📧 Email: beingtush@gmail.com
- 💼 GitHub: [@beingtush](https://github.com/beingtush)
- 🔗 Repository: [rest-assured-practice](https://github.com/beingtush/rest-assured-practice)

---

## ⭐ Show Your Support

If you found this project helpful, please give it a ⭐️! It helps others discover this resource.

[![GitHub Stars](https://img.shields.io/github/stars/beingtush/rest-assured-practice?style=social)](https://github.com/beingtush/rest-assured-practice/stargazers)

---

## 📖 Additional Resources

- [REST Assured Documentation](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/doc/)
- [Allure Framework](https://docs.qameta.io/allure/)
- [API Testing Best Practices](https://www.postman.com/api-testing/)

---

## 🎓 Learning Path

**New to API Testing?** Follow this learning path:

1. Start with `BasicGetRequestTest.java`
2. Progress to `PostRequestTest.java`
3. Learn authentication in `AuthenticationTest.java`
4. Explore serialization in `SerializationDeserializationTest.java`
5. Master schema validation in `JsonSchemaValidationTest.java`

---

## 📝 Keywords for SEO

API Testing, REST Assured, Java API Testing, Test Automation, REST API, API Test Framework, TestNG, Maven, Allure Reports, BDD Testing, HTTP Testing, JSON Validation, API Automation, Continuous Testing, QA Automation, Software Testing, REST Assured Tutorial, API Testing Examples, Java Testing Framework, REST Assured Framework

---

<div align="center">

**Made with ❤️ by [Tushar Raj](https://github.com/beingtush)**

**Happy Testing! 🚀**

</div>
