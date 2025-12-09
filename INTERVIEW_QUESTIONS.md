# RestAssured & API Testing Interview Questions

Complete interview preparation guide with 150+ questions ranging from basic one-word answers to complex coding scenarios.

---

## 📚 Table of Contents

1. [Basic Questions (1-50)](#basic-questions)
2. [Intermediate Questions (51-80)](#intermediate-questions)
3. [Advanced Questions (81-100)](#advanced-questions)
4. [Scenario-Based Questions (101-120)](#scenario-based-questions)
5. [Coding Questions (121-140)](#coding-questions)
6. [Tricky Interview Questions (141-150)](#tricky-questions)

---

## Basic Questions

### One-Word / Short Answers (Q1-Q30)

**Q1. What is RestAssured?**  
A Java library for testing RESTful APIs.

**Q2. Which language is RestAssured written in?**  
Java

**Q3. What testing style does RestAssured support?**  
BDD (Behavior Driven Development) - Given-When-Then

**Q4. What is the default content type in RestAssured?**  
application/json

**Q5. What does REST stand for?**  
Representational State Transfer

**Q6. What HTTP status code indicates success?**  
200 (OK)

**Q7. What status code indicates resource created?**  
201 (Created)

**Q8. What status code indicates resource not found?**  
404 (Not Found)

**Q9. What status code indicates unauthorized?**  
401 (Unauthorized)

**Q10. What status code indicates forbidden?**  
403 (Forbidden)

**Q11. What status code indicates server error?**  
500 (Internal Server Error)

**Q12. What HTTP method retrieves data?**  
GET

**Q13. What HTTP method creates data?**  
POST

**Q14. What HTTP method updates data completely?**  
PUT

**Q15. What HTTP method updates data partially?**  
PATCH

**Q16. What HTTP method deletes data?**  
DELETE

**Q17. What library does RestAssured use for assertions?**  
Hamcrest

**Q18. What is the base package for RestAssured?**  
io.rest-assured

**Q19. What is an API endpoint?**  
A specific URL where an API can be accessed

**Q20. What data format is commonly used in REST?**  
JSON (JavaScript Object Notation)

**Q21. What is the default HTTP port?**  
80

**Q22. What is the default HTTPS port?**  
443

**Q23. What does API stand for?**  
Application Programming Interface

**Q24. What is a request header?**  
Metadata sent with an API request

**Q25. What is a response body?**  
The actual data returned by the API

**Q26. What is serialization?**  
Converting Java object to JSON

**Q27. What is deserialization?**  
Converting JSON to Java object

**Q28. What is POJO?**  
Plain Old Java Object

**Q29. What is JSON?**  
JavaScript Object Notation - a data format

**Q30. What is XML?**  
eXtensible Markup Language - a data format

### REST & HTTP Concepts (Q31-Q50)

**Q31. What is idempotency?**  
An operation that produces the same result when called multiple times.

**Q32. Which HTTP methods are idempotent?**  
GET, PUT, DELETE, HEAD, OPTIONS

**Q33. Which HTTP method is NOT idempotent?**  
POST

**Q34. What is the difference between PUT and PATCH?**  
PUT = complete update, PATCH = partial update

**Q35. What is Content-Type header?**  
Indicates the media type of request body

**Q36. What is Accept header?**  
Indicates what media types client can understand

**Q37. What is Authorization header used for?**  
Sending authentication credentials

**Q38. What is Bearer token?**  
An access token sent as: Bearer <token>

**Q39. What is API versioning?**  
Managing different versions of an API (v1, v2, etc.)

**Q40. What is CORS?**  
Cross-Origin Resource Sharing

**Q41. What is rate limiting?**  
Restricting number of API requests in a time period

**Q42. What status code indicates rate limiting?**  
429 (Too Many Requests)

**Q43. What is pagination?**  
Breaking large data sets into smaller pages

**Q44. What are query parameters?**  
Parameters after ? in URL (?page=1&limit=10)

**Q45. What are path parameters?**  
Variables in URL path (/users/{id})

**Q46. What is base URI?**  
The root URL for all API endpoints

**Q47. What is JSON Path?**  
A way to navigate and extract JSON data

**Q48. What is XML Path?**  
A way to navigate and extract XML data

**Q49. What is API mocking?**  
Creating fake API responses for testing

**Q50. What is API contract testing?**  
Verifying producer and consumer agree on API format

---

## Intermediate Questions

**Q51. Explain the Given-When-Then pattern.**  
- **Given:** Pre-conditions (base URI, headers, params, body)
- **When:** Action (HTTP method - GET, POST, etc.)
- **Then:** Validation (status code, response body, headers)

**Q52. What is the difference between queryParam() and pathParam()?**  
- queryParam: Adds after ? (/users?id=1)
- pathParam: Replaces placeholder (/users/{id})

**Q53. What is RequestSpecification?**  
A reusable specification for common request configurations.

**Q54. What is ResponseSpecification?**  
A reusable specification for common response validations.

**Q55. Name 5 Hamcrest matchers.**  
1. equalTo()
2. notNullValue()
3. greaterThan()
4. containsString()
5. hasSize()

**Q56. How do you log requests in RestAssured?**  
Using .log().all(), .log().body(), .log().headers()

**Q57. What is the difference between basic auth and preemptive basic auth?**  
- Basic: Sends credentials after 401 challenge
- Preemptive: Sends credentials with first request

**Q58. What authentication methods does RestAssured support?**  
Basic, Digest, Form, OAuth 1.0, OAuth 2.0, Certificate

**Q59. How do you validate response time?**  
Using .time(lessThan(3000L))

**Q60. How do you extract a value from response?**  
Using .extract().path("fieldName")

**Q61. What is multipart form data?**  
Form data that can include files (used for uploads)

**Q62. What is the difference between formParam() and multiPart()?**  
- formParam: URL-encoded form data
- multiPart: Multipart/form-data (for file uploads)

**Q63. How do you validate nested JSON fields?**  
Using dot notation: .body("address.city", equalTo("NY"))

**Q64. How do you validate arrays in JSON?**  
Using [index] or size(): .body("[0].id", equalTo(1))

**Q65. What is JSON Schema validation?**  
Validating structure and data types against predefined schema

**Q66. How do you handle dynamic JSON responses?**  
Using JsonPath with wildcards or iteration

**Q67. What is a Filter in RestAssured?**  
Custom logic to intercept and modify requests/responses

**Q68. What is the difference between baseURI and basePath?**  
- baseURI: Base URL (https://api.example.com)
- basePath: Common path prefix (/api/v1)

**Q69. How do you handle cookies in RestAssured?**  
Using .cookie("name", "value")

**Q70. How do you validate response headers?**  
Using .header("headerName", equalTo("value"))

**Q71. What is REST vs SOAP?**  
REST: Architectural style, uses JSON/XML, lighter
SOAP: Protocol, only XML, heavier

**Q72. What are HTTP status code categories?**  
1xx: Informational, 2xx: Success, 3xx: Redirection, 4xx: Client Error, 5xx: Server Error

**Q73. What is API rate limiting testing?**  
Testing that API enforces request limits per time period

**Q74. What is OAuth 2.0?**  
Authorization framework for limited access to resources

**Q75. What is API key authentication?**  
Authentication using unique key in header or query param

**Q76. How do you handle file downloads?**  
Using .extract().asByteArray()

**Q77. How do you handle file uploads?**  
Using .multiPart("file", new File("path"))

**Q78. What is Request/Response logging?**  
Capturing request and response details for debugging

**Q79. What is the difference between .get() and .when().get()?**  
.when().get() is part of BDD style (Given-When-Then)

**Q80. What build tools support RestAssured?**  
Maven and Gradle

---

## Advanced Questions

**Q81. Explain RequestSpecBuilder.**  
```java
RequestSpecification spec = new RequestSpecBuilder()
    .setBaseUri("https://api.example.com")
    .setContentType(ContentType.JSON)
    .addHeader("Authorization", "Bearer token")
    .build();
```
Used to create reusable request specifications.

**Q82. How do you implement custom filters?**  
```java
public class CustomFilter implements Filter {
    public Response filter(FilterableRequestSpecification req,
                          FilterableResponseSpecification res,
                          FilterContext ctx) {
        Response response = ctx.next(req, res);
        return response;
    }
}
```

**Q83. How do you handle authentication token expiry?**  
- Store token with expiry time
- Check before each request
- Refresh if expired
- Use filters for automatic refresh

**Q84. What is the difference between response time and latency?**  
- Response time: Total time from request to response
- Latency: Server processing time (excludes network)

**Q85. How do you test CORS headers?**  
```java
given()
    .header("Origin", "https://example.com")
.when()
    .options("/api/endpoint")
.then()
    .header("Access-Control-Allow-Origin", "*");
```

**Q86. How do you implement retry logic?**  
Using TestNG retryAnalyzer or custom try-catch loops

**Q87. How do you test API versioning?**  
Test different versions via URL, header, or query param

**Q88. How do you handle gzip compression?**  
RestAssured automatically decompresses gzip responses

**Q89. What is contract testing vs functional testing?**  
- Contract: Verify API structure/format
- Functional: Verify business logic

**Q90. How do you validate response against schema?**  
```java
.then()
    .body(matchesJsonSchemaInClasspath("schema.json"));
```

**Q91. How do you test pagination?**  
Test page numbers, limits, total count, first/last pages

**Q92. How do you handle large datasets?**  
Use pagination, streaming, test response time

**Q93. What is API smoke testing?**  
Basic tests to verify critical endpoints work

**Q94. What is API regression testing?**  
Re-testing to ensure changes don't break existing functionality

**Q95. How do you test error responses?**  
Test 4xx and 5xx status codes, error messages, error structure

**Q96. What is data-driven testing in RestAssured?**  
Running same test with different data sets using TestNG DataProvider

**Q97. How do you organize API tests?**  
By endpoint, by functionality, by test type (smoke, regression)

**Q98. What is the difference between integration and E2E testing?**  
- Integration: Test interactions between components
- E2E: Test complete user workflows

**Q99. How do you mock API responses?**  
Using tools like WireMock, MockServer, or Mockito

**Q100. What is API performance testing?**  
Testing response time, throughput, scalability using JMeter/Gatling

---

## Scenario-Based Questions

**Q101. API requires OAuth 2.0. How do you test it?**  
```java
// Get token
String token = given()
    .formParam("client_id", "...")
    .formParam("client_secret", "...")
    .formParam("grant_type", "client_credentials")
.when()
    .post("/oauth/token")
.then()
    .extract().path("access_token");

// Use token
given()
    .auth().oauth2(token)
.when()
    .get("/api/protected")
.then()
    .statusCode(200);
```

**Q102. API has rate limiting. How do you test?**  
Make multiple rapid requests and verify 429 status after limit reached.

**Q103. API returns different structures on success/failure. How to handle?**  
Check status code first, then validate appropriate structure.

**Q104. How do you test an API that's intermittently failing?**  
- Enable detailed logging
- Test at different times
- Implement retry logic
- Check for rate limiting
- Verify network stability

**Q105. API accepts only specific Content-Types. How to test?**  
```java
// Valid
given().contentType(ContentType.JSON)
    .body("{}").when().post("/api").then().statusCode(200);

// Invalid
given().contentType(ContentType.XML)
    .body("<root/>").when().post("/api").then().statusCode(415);
```

**Q106. How do you test timeout scenarios?**  
```java
given()
    .timeout(Duration.ofSeconds(5))
.when()
    .get("/slow-endpoint")
.then()
    .time(lessThan(5000L));
```

**Q107. API endpoint requires multiple headers. How to manage?**  
```java
Map<String, String> headers = new HashMap<>();
headers.put("Authorization", "Bearer token");
headers.put("Content-Type", "application/json");
headers.put("Accept-Language", "en-US");

given().headers(headers)...
```

**Q108. How do you test file upload with metadata?**  
```java
given()
    .multiPart("file", new File("test.pdf"))
    .multiPart("description", "Test document")
    .multiPart("category", "reports")
.when()
    .post("/upload")
.then()
    .statusCode(200);
```

**Q109. API returns paginated data. How to fetch all?**  
```java
int page = 1;
List<Object> allData = new ArrayList<>();
while (true) {
    Response response = given()
        .queryParam("page", page)
        .get("/api/data");
    
    List<Object> pageData = response.jsonPath().getList("data");
    if (pageData.isEmpty()) break;
    
    allData.addAll(pageData);
    page++;
}
```

**Q110. How do you test API that requires sequential calls?**  
```java
// Step 1: Login
String token = given()...post("/login")...extract().path("token");

// Step 2: Create resource
int id = given().auth().oauth2(token)...post("/resource")...extract().path("id");

// Step 3: Update resource
given().auth().oauth2(token)...put("/resource/" + id)...
```

**Q111. How do you validate dynamic timestamps in responses?**  
```java
String timestamp = response.path("createdAt");
LocalDateTime created = LocalDateTime.parse(timestamp);
assert created.isBefore(LocalDateTime.now());
```

**Q112. API returns base64 encoded data. How to validate?**  
```java
String base64Data = response.path("data");
byte[] decoded = Base64.getDecoder().decode(base64Data);
String decodedString = new String(decoded);
assert decodedString.contains("expected content");
```

**Q113. How do you test webhook endpoints?**  
Set up test server to receive webhooks, trigger action, verify webhook received.

**Q114. How do you handle flaky API tests?**  
- Implement retry logic
- Add wait times
- Check for race conditions
- Verify test data state
- Use better assertions

**Q115. API uses custom authentication header. How to handle?**  
```java
given()
    .header("X-Custom-Auth-Token", "custom-token-value")
.when()
    .get("/api/endpoint")...
```

**Q116. How do you test API with geo-location requirements?**  
Send location headers or test from different regions:
```java
given()
    .header("X-Forwarded-For", "203.0.113.195")
    .header("X-Country-Code", "US")
.when()
    .get("/api/geo-specific")...
```

**Q117. How do you test API caching?**  
Make same request twice, verify:
- Second response is faster
- Cache headers present
- Etag/If-None-Match handling

**Q118. API requires request signing. How to implement?**  
```java
String signature = generateSignature(requestData, secretKey);
given()
    .header("X-Signature", signature)
    .body(requestData)
.when()
    .post("/api/secure")...
```

**Q119. How do you test API with file size restrictions?**  
```java
// Test valid size
given().multiPart("file", smallFile).post("/upload")
    .then().statusCode(200);

// Test oversized file
given().multiPart("file", largeFile).post("/upload")
    .then().statusCode(413); // Payload Too Large
```

**Q120. How do you test API backward compatibility?**  
Run tests against old and new versions, ensure old clients still work.

---

## Coding Questions

**Q121. Write a test to validate all users have valid emails.**  
```java
@Test
public void testValidEmails() {
    given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .statusCode(200)
        .body("email", everyItem(containsString("@")))
        .body("email", everyItem(containsString(".")));
}
```

**Q122. Create a user and verify details.**  
```java
@Test
public void testCreateUser() {
    Map<String, String> user = new HashMap<>();
    user.put("name", "John Doe");
    user.put("job", "QA Engineer");
    
    given()
        .contentType(ContentType.JSON)
        .body(user)
    .when()
        .post("https://reqres.in/api/users")
    .then()
        .statusCode(201)
        .body("name", equalTo("John Doe"))
        .body("job", equalTo("QA Engineer"))
        .body("id", notNullValue());
}
```

**Q123. Write test using POJO.**  
```java
@Data
@Builder
class User {
    private String name;
    private String job;
    private String id;
}

@Test
public void testWithPOJO() {
    User user = User.builder()
        .name("Jane")
        .job("Developer")
        .build();
    
    User created = given()
        .contentType(ContentType.JSON)
        .body(user)
    .when()
        .post("https://reqres.in/api/users")
    .then()
        .statusCode(201)
        .extract().as(User.class);
    
    assertEquals(created.getName(), "Jane");
    assertNotNull(created.getId());
}
```

**Q124. Fetch posts by user and verify count.**  
```java
@Test
public void testPostsByUser() {
    given()
        .queryParam("userId", 1)
    .when()
        .get("https://jsonplaceholder.typicode.com/posts")
    .then()
        .statusCode(200)
        .body("size()", greaterThan(0))
        .body("userId", everyItem(equalTo(1)));
}
```

**Q125. Update resource and verify.**  
```java
@Test
public void testUpdate() {
    Map<String, Object> update = new HashMap<>();
    update.put("title", "Updated Title");
    update.put("body", "Updated Body");
    update.put("userId", 1);
    
    given()
        .contentType(ContentType.JSON)
        .body(update)
        .pathParam("id", 1)
    .when()
        .put("https://jsonplaceholder.typicode.com/posts/{id}")
    .then()
        .statusCode(200)
        .body("title", equalTo("Updated Title"));
}
```

**Q126. Write test with Request and Response specs.**  
```java
@Test
public void testWithSpecs() {
    RequestSpecification reqSpec = new RequestSpecBuilder()
        .setBaseUri("https://jsonplaceholder.typicode.com")
        .setContentType(ContentType.JSON)
        .build();
    
    ResponseSpecification resSpec = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .expectContentType(ContentType.JSON)
        .build();
    
    given().spec(reqSpec)
    .when().get("/posts/1")
    .then().spec(resSpec);
}
```

**Q127. Data-driven test with TestNG DataProvider.**  
```java
@DataProvider(name = "userIds")
public Object[][] getUserIds() {
    return new Object[][] {{1}, {2}, {3}, {4}, {5}};
}

@Test(dataProvider = "userIds")
public void testUsers(int userId) {
    given()
        .pathParam("id", userId)
    .when()
        .get("https://jsonplaceholder.typicode.com/users/{id}")
    .then()
        .statusCode(200)
        .body("id", equalTo(userId));
}
```

**Q128. Validate JSON schema.**  
```java
@Test
public void testSchema() {
    given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users/1")
    .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
}
```

**Q129. Handle error responses.**  
```java
@Test
public void testErrors() {
    // 404
    given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/9999")
    .then()
        .statusCode(404);
    
    // 400
    given()
        .contentType(ContentType.JSON)
        .body("{}")
    .when()
        .post("https://reqres.in/api/register")
    .then()
        .statusCode(400)
        .body("error", notNullValue());
}
```

**Q130. File upload test.**  
```java
@Test
public void testFileUpload() {
    File file = new File("src/test/resources/test.txt");
    
    given()
        .multiPart("file", file)
    .when()
        .post("https://httpbin.org/post")
    .then()
        .statusCode(200)
        .body("files", notNullValue());
}
```

**Q131. Extract and validate nested data.**  
```java
@Test
public void testNested() {
    given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users/1")
    .then()
        .statusCode(200)
        .body("address.city", notNullValue())
        .body("address.geo.lat", notNullValue())
        .body("company.name", not(emptyString()));
}
```

**Q132. Validate array elements.**  
```java
@Test
public void testArray() {
    given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .statusCode(200)
        .body("size()", equalTo(10))
        .body("[0].id", equalTo(1))
        .body("id", everyItem(greaterThan(0)))
        .body("email", everyItem(containsString("@")));
}
```

**Q133. Delete and verify.**  
```java
@Test
public void testDelete() {
    given()
        .pathParam("id", 1)
    .when()
        .delete("https://jsonplaceholder.typicode.com/posts/{id}")
    .then()
        .statusCode(200);
}
```

**Q134. Test authentication.**  
```java
@Test
public void testAuth() {
    given()
        .auth().basic("user", "passwd")
    .when()
        .get("https://httpbin.org/basic-auth/user/passwd")
    .then()
        .statusCode(200)
        .body("authenticated", equalTo(true));
}
```

**Q135. Test with headers.**  
```java
@Test
public void testHeaders() {
    given()
        .header("Custom-Header", "Value")
        .header("User-Agent", "RestAssured")
    .when()
        .get("https://httpbin.org/headers")
    .then()
        .statusCode(200)
        .body("headers.Custom-Header", equalTo("Value"));
}
```

**Q136. Test with cookies.**  
```java
@Test
public void testCookies() {
    given()
        .cookie("session", "abc123")
        .cookie("user", "john")
    .when()
        .get("https://httpbin.org/cookies")
    .then()
        .statusCode(200)
        .body("cookies.session", equalTo("abc123"));
}
```

**Q137. Validate response time.**  
```java
@Test
public void testResponseTime() {
    given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .time(lessThan(2000L))
        .statusCode(200);
}
```

**Q138. Extract multiple values.**  
```java
@Test
public void testExtract() {
    Response response = given()
        .when().get("https://jsonplaceholder.typicode.com/posts/1")
        .then().extract().response();
    
    int userId = response.path("userId");
    String title = response.path("title");
    String body = response.path("body");
    
    System.out.println("User: " + userId);
    System.out.println("Title: " + title);
}
```

**Q139. Chain API calls.**  
```java
@Test
public void testChainedCalls() {
    // Create user
    String userId = given()
        .contentType(ContentType.JSON)
        .body("{\"name\":\"John\",\"job\":\"QA\"}")
    .when()
        .post("https://reqres.in/api/users")
    .then()
        .statusCode(201)
        .extract().path("id");
    
    // Use userId in next call
    System.out.println("Created user: " + userId);
}
```

**Q140. Test with form parameters.**  
```java
@Test
public void testFormParams() {
    given()
        .contentType("application/x-www-form-urlencoded")
        .formParam("username", "john")
        .formParam("password", "secret")
    .when()
        .post("https://httpbin.org/post")
    .then()
        .statusCode(200)
        .body("form.username", equalTo("john"));
}
```

---

## Tricky Interview Questions

**Q141. Can you perform UI testing with RestAssured?**  
No, RestAssured is specifically for API testing. For UI, use Selenium.

**Q142. Is RestAssured a framework or library?**  
Library. It's used with testing frameworks like TestNG or JUnit.

**Q143. Can RestAssured test GraphQL APIs?**  
Yes, by sending GraphQL queries as POST requests with JSON body.

**Q144. What's the difference between .body() in given() and then()?**  
- given().body(): Sets request body
- then().body(): Validates response body

**Q145. Can you test WebSocket APIs with RestAssured?**  
No, RestAssured is for REST APIs. Use Java WebSocket Client for WebSocket.

**Q146. Is RestAssured thread-safe?**  
Static configurations (RestAssured.baseURI) are not thread-safe. Use RequestSpecification for parallel execution.

**Q147. Can RestAssured handle binary data?**  
Yes, using .asByteArray() for responses and multiPart() for uploads.

**Q148. What's better: Postman or RestAssured?**  
- Postman: Manual testing, quick validation, GUI
- RestAssured: Automation, CI/CD integration, code-based

**Q149. Can RestAssured replace performance testing tools?**  
No, RestAssured is for functional testing. Use JMeter/Gatling for performance.

**Q150. Why use RestAssured when you can use HttpClient?**  
RestAssured provides:
- BDD style syntax
- Built-in assertions with Hamcrest
- Easy JSON/XML parsing
- Less boilerplate code
- Better readability

---

## 🎯 Interview Tips

### Preparation Strategy:
1. **Start with basics** - Know HTTP methods, status codes
2. **Practice coding** - Write actual tests
3. **Understand concepts** - Don't just memorize
4. **Explain clearly** - Use examples
5. **Show projects** - Discuss your experience

### Common Follow-up Questions:
- "How did you use this in your project?"
- "What challenges did you face?"
- "How did you solve [specific problem]?"
- "Can you write code for this scenario?"

### Red Flags to Avoid:
- ❌ Saying "I never faced that issue"
- ❌ Memorizing without understanding
- ❌ Unable to write basic code
- ❌ Not knowing difference between similar concepts
- ❌ Can't explain your own project decisions

### Impressive Answers Include:
- ✅ Real project examples
- ✅ Problem-solving approach
- ✅ Best practices you follow
- ✅ Tools and frameworks you've used
- ✅ Challenges overcome

---

## 📚 Additional Topics to Study

1. **CI/CD Integration** - Jenkins, GitHub Actions
2. **Reporting** - Allure, Extent Reports
3. **Mocking** - WireMock, MockServer
4. **Performance** - JMeter, Gatling
5. **Security Testing** - OWASP API Security
6. **Contract Testing** - Pact
7. **Data Management** - Test data strategies
8. **Environment Management** - Dev, QA, Prod configs
9. **API Documentation** - Swagger, OpenAPI
10. **Version Control** - Git basics

---

## 🚀 Practice Resources

- **Public APIs for Practice:**
  - JSONPlaceholder: https://jsonplaceholder.typicode.com
  - ReqRes: https://reqres.in
  - HTTPBin: https://httpbin.org
  - REST Countries: https://restcountries.com

- **Official Documentation:**
  - RestAssured: https://rest-assured.io
  - Hamcrest: http://hamcrest.org
  - TestNG: https://testng.org

---

**Good Luck with Your Interview! 🎉**

Remember: The key is not just knowing the answers, but understanding the concepts and being able to apply them in real scenarios.
