# RestAssured Practice Project

A comprehensive RestAssured practice project covering all major API testing concepts.

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Internet connection (for API calls)

## Project Structure

```
RestAssured Practice/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/restassured/practice/
│   │           ├── models/        # POJO classes
│   │           └── utils/         # Utility classes
│   └── test/
│       ├── java/
│       │   └── com/restassured/practice/tests/  # Test classes
│       └── resources/
│           ├── schemas/           # JSON schemas
│           └── testdata/          # Test data files
├── pom.xml                        # Maven dependencies
└── testng.xml                     # TestNG suite configuration
```

## Setup Instructions

1. Open the project in your IDE (IntelliJ IDEA, Eclipse, or VS Code)
2. Run `mvn clean install` to download all dependencies
3. Run tests using `mvn test` or through your IDE

## Topics Covered

### 1. **Basic GET Requests**
   - Simple GET requests
   - Response validation
   - Status code verification
   - Response time assertions

### 2. **POST Requests**
   - Creating resources
   - Request body with different formats
   - Response validation

### 3. **PUT, PATCH, DELETE**
   - Update operations
   - Partial updates
   - Delete operations

### 4. **Query Parameters**
   - Single and multiple parameters
   - Parameter encoding

### 5. **Path Parameters**
   - Dynamic URL paths
   - Path variable substitution

### 6. **Headers & Cookies**
   - Custom headers
   - Cookie handling
   - Content-Type negotiation

### 7. **Authentication**
   - Basic Auth
   - Bearer Token
   - API Key authentication

### 8. **JSON Schema Validation**
   - Schema validation
   - Structure verification

### 9. **Serialization & Deserialization**
   - POJO to JSON
   - JSON to POJO
   - Complex object handling

### 10. **File Upload & Download**
   - Multipart form data
   - File download
   - Binary data handling

## Public APIs Used

- **JSONPlaceholder** (https://jsonplaceholder.typicode.com/) - Fake REST API
- **ReqRes** (https://reqres.in/) - REST API for testing
- **HTTPBin** (https://httpbin.org/) - HTTP request & response service
- **REST Countries** (https://restcountries.com/) - Country information API

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=BasicGetRequestTest
```

### Run tests from IDE:
Right-click on test class or testng.xml and select "Run"

## Best Practices Demonstrated

1. ✅ Given-When-Then pattern (BDD style)
2. ✅ Request and Response specifications
3. ✅ Log filters for debugging
4. ✅ Response validation with Hamcrest matchers
5. ✅ JSON Path and XML Path
6. ✅ POJO serialization/deserialization
7. ✅ Schema validation
8. ✅ Proper assertions
9. ✅ Test organization with TestNG

## Notes

- All tests use public, free APIs that don't require authentication (except auth examples)
- Tests are independent and can run in any order
- Some tests may fail if the public APIs are down or rate-limited
- Modify base URIs in tests if needed

## Happy Testing! 🚀
