# Frequently Asked Questions (FAQ)

Common questions about the REST Assured API Testing Framework.

---

## General Questions

### What is REST Assured?

REST Assured is a Java library that simplifies testing REST APIs. It provides a domain-specific language (DSL) for writing powerful, maintainable tests with minimal code.

### Do I need programming experience?

Basic Java knowledge is recommended. If you're new to Java, start with simple GET requests and gradually progress to more complex scenarios.

### Which Java version do I need?

Java 11 or higher is required. The framework is tested on Java 11, 17, and 21.

### Can I use this with JUnit instead of TestNG?

Yes! While this project uses TestNG, REST Assured works equally well with JUnit. You'll need to replace TestNG annotations with JUnit equivalents.

---

## Setup Questions

### How do I import this project into my IDE?

**IntelliJ IDEA:**
1. File → Open → Select `pom.xml`
2. Open as Project

**Eclipse:**
1. File → Import → Maven → Existing Maven Projects
2. Select project folder

**VS Code:**
1. Open folder
2. Install Java Extension Pack

### Why are my dependencies not downloading?

Try:
```bash
mvn clean install -U
```

If that doesn't work, delete `~/.m2/repository` and run again.

### How do I fix "Java version mismatch" errors?

Check your Java version matches the pom.xml configuration:
```bash
java -version
```

Update pom.xml if needed:
```xml
<maven.compiler.source>11</maven.compiler.source>
<maven.compiler.target>11</maven.compiler.target>
```

---

## Testing Questions

### How do I run a single test?

```bash
mvn test -Dtest=BasicGetRequestTest#testGetAllUsers
```

Or right-click the test in your IDE and select "Run".

### How do I run tests with specific tags/groups?

```bash
mvn test -Dgroups=smoke
```

### Why are my tests failing with "Connection refused"?

Common causes:
1. No internet connection
2. API endpoint is down
3. Firewall blocking the request
4. Wrong URL

Test the endpoint in a browser or Postman first.

### How do I make tests run faster?

1. Run tests in parallel (configure in testng.xml)
2. Use request/response specifications to avoid repetition
3. Minimize assertions to only what's necessary
4. Use local mock servers when possible

---

## REST Assured Questions

### What's the difference between `.get()` and `.when().get()`?

They're the same! REST Assured's BDD style allows:
```java
// Both are equivalent
get("/users");

when()
    .get("/users");
```

The `when()` is optional but recommended for clarity.

### How do I extract data from a response?

```java
// Extract single value
String name = response.path("name");

// Extract to POJO
User user = response.as(User.class);

// Extract list
List<String> names = response.path("data.name");
```

### How do I send headers?

```java
given()
    .header("Content-Type", "application/json")
    .header("Authorization", "Bearer token")
.when()
    .get("/endpoint");
```

### How do I send query parameters?

```java
given()
    .queryParam("page", 1)
    .queryParam("limit", 10)
.when()
    .get("/users");
```

### How do I send a JSON body?

**Option 1: String**
```java
String json = """
    {
        "name": "John",
        "email": "john@example.com"
    }
    """;

given()
    .body(json)
.when()
    .post("/users");
```

**Option 2: POJO**
```java
User user = User.builder()
    .name("John")
    .email("john@example.com")
    .build();

given()
    .body(user)
.when()
    .post("/users");
```

### How do I validate JSON schema?

```java
given()
    .get("/users/1")
.then()
    .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
```

### How do I handle authentication?

**Basic Auth:**
```java
given()
    .auth()
    .basic("username", "password")
```

**Bearer Token:**
```java
given()
    .auth()
    .oauth2("your-token")
```

**API Key:**
```java
given()
    .header("X-API-Key", "your-key")
```

---

## Framework Questions

### What are Request Specifications?

Reusable configurations for requests:
```java
RequestSpecification spec = new RequestSpecBuilder()
    .setBaseUri("https://api.example.com")
    .setContentType(ContentType.JSON)
    .build();

given()
    .spec(spec)
.when()
    .get("/endpoint");
```

### What are the utility classes for?

- **ApiConfig**: Centralized request/response specifications
- **TestDataGenerator**: Generate random test data
- **JsonUtils**: JSON serialization/deserialization helpers
- **ConfigReader**: Read properties files

### How do I add a new API to test?

1. Add request spec in `ApiConfig.java`
2. Create a new test class in `src/test/java/.../tests/`
3. Write tests using the spec
4. Run and verify

### Can I use this framework for SOAP APIs?

This framework is optimized for REST APIs. For SOAP, consider using a SOAP-specific library.

---

## Data Management Questions

### Should I use hardcoded test data?

No! Use `TestDataGenerator` to create unique data:
```java
User user = TestDataGenerator.generateRandomUser();
```

This prevents data conflicts and flaky tests.

### How do I handle test data cleanup?

```java
@AfterMethod
public void cleanup() {
    // Delete created resources
    deleteUser(userId);
}
```

### How do I read test data from files?

```java
User user = JsonUtils.readFromFile("testdata/user.json", User.class);
```

---

## Debugging Questions

### How do I see the request/response?

```java
given()
    .log().all()  // Log request
.when()
    .get("/users")
.then()
    .log().all()  // Log response
    .statusCode(200);
```

### How do I debug a failing test?

1. Enable logging: `.log().all()`
2. Print response: `response.prettyPrint()`
3. Check response status: `response.statusCode()`
4. Validate response structure matches expectations
5. Run test in debug mode in IDE

### Why is my assertion failing?

Common causes:
1. Wrong JSON path
2. Type mismatch (String vs Integer)
3. Response structure different than expected
4. Null values

Print the response to verify:
```java
response.prettyPrint();
```

---

## Performance Questions

### How do I test API performance?

```java
given()
    .get("/users")
.then()
    .time(lessThan(2000L));  // Must respond in < 2 seconds
```

### Can I do load testing with REST Assured?

Yes, but it's not ideal for heavy load testing. For that, use JMeter or Gatling. REST Assured is better for functional testing with some performance validation.

```java
@Test(threadPoolSize = 10, invocationCount = 100)
public void concurrentTest() {
    given().get("/users").then().statusCode(200);
}
```

---

## CI/CD Questions

### How do I run tests in GitHub Actions?

The project includes a GitHub Actions workflow. Just push your code:
```bash
git push origin main
```

Tests run automatically!

### Can I integrate with Jenkins?

Yes! Add this to your Jenkins pipeline:
```groovy
stage('Test') {
    steps {
        sh 'mvn clean test'
    }
}
```

### How do I generate reports in CI?

The workflow automatically generates Allure reports. They're uploaded as artifacts you can download.

---

## Reporting Questions

### How do I generate Allure reports?

```bash
mvn clean test
mvn allure:serve
```

### Where are test results stored?

- TestNG results: `target/surefire-reports/`
- Allure results: `allure-results/`

### Can I customize the reports?

Yes! Allure supports custom labels, descriptions, and attachments. Use annotations:
```java
@Test
@Description("Test user creation")
@Severity(SeverityLevel.CRITICAL)
public void testCreateUser() { }
```

---

## Best Practices Questions

### Should tests depend on each other?

No! Tests should be independent. Each test should:
1. Create its own test data
2. Execute its logic
3. Clean up after itself

### How many assertions should a test have?

Test one logical scenario per test, but validate multiple aspects:
```java
@Test
public void testGetUser() {
    given()
        .get("/users/1")
    .then()
        .statusCode(200)           // Validate status
        .contentType(ContentType.JSON)  // Validate content type
        .body("id", equalTo(1))    // Validate data
        .body("name", notNullValue());
}
```

### Should I test in production?

Generally no. Use dedicated test environments. If you must test production:
- Use read-only operations
- Don't create/modify real data
- Have proper monitoring

---

## Contributing Questions

### How do I contribute?

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

See [CONTRIBUTING.md](../CONTRIBUTING.md) for details.

### What kind of contributions are welcome?

- New test examples
- Bug fixes
- Documentation improvements
- New utility classes
- Performance improvements

### How do I report a bug?

Open an issue on GitHub with:
- Clear description
- Steps to reproduce
- Expected vs actual behavior
- Environment details

---

## Advanced Questions

### Can I use this with Cucumber?

Yes! You can integrate REST Assured with Cucumber for BDD. You'll need to add Cucumber dependencies and create step definitions.

### How do I mock API responses?

Consider using WireMock or MockServer alongside REST Assured:
```java
@Test
public void testWithMock() {
    stubFor(get("/users/1")
        .willReturn(ok().withBody("{\"id\":1,\"name\":\"John\"}")));
    
    // Test against mock
}
```

### Can I test GraphQL APIs?

Yes! REST Assured can test any HTTP-based API, including GraphQL:
```java
String query = "{\"query\":\"{ users { id name } }\"}";

given()
    .body(query)
.when()
    .post("/graphql")
.then()
    .statusCode(200);
```

### How do I handle file uploads?

```java
given()
    .multiPart("file", new File("test.pdf"))
.when()
    .post("/upload")
.then()
    .statusCode(200);
```

---

## Troubleshooting

### Still having issues?

1. Check [Troubleshooting Guide](TROUBLESHOOTING.md)
2. Review [GitHub Issues](https://github.com/beingtush/rest-assured-practice/issues)
3. Ask in [REST Assured Forums](https://groups.google.com/g/rest-assured)
4. Open a new issue with details

---

## Additional Resources

- [Getting Started Guide](GETTING_STARTED.md)
- [API Reference](API_REFERENCE.md)
- [Best Practices](BEST_PRACTICES.md)
- [REST Assured Docs](https://rest-assured.io/)
- [TestNG Docs](https://testng.org/doc/)

---

**Have a question not listed here? Open an issue and we'll add it!**

📧 Contact: beingtush@gmail.com
