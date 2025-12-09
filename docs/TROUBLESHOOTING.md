# Troubleshooting Guide

Common issues and their solutions when working with REST Assured API Testing Framework.

---

## Table of Contents

- [Setup Issues](#setup-issues)
- [Test Execution Issues](#test-execution-issues)
- [REST Assured Issues](#rest-assured-issues)
- [Maven Issues](#maven-issues)
- [TestNG Issues](#testng-issues)
- [JSON/XML Issues](#jsonxml-issues)
- [Network Issues](#network-issues)
- [CI/CD Issues](#cicd-issues)

---

## Setup Issues

### Issue: Maven dependencies not downloading

**Symptoms:**
- Build fails with "cannot find symbol" errors
- Classes like RestAssured not found

**Solutions:**

1. Force update Maven dependencies:
```bash
mvn clean install -U
```

2. Delete local Maven repository cache:
```bash
# Windows
rmdir /s "%USERPROFILE%\.m2\repository"

# Linux/Mac
rm -rf ~/.m2/repository
```

3. Check Maven settings.xml for proxy/mirror issues

### Issue: Java version mismatch

**Symptoms:**
- "Unsupported class file major version" error
- Compilation fails

**Solutions:**

1. Check Java version:
```bash
java -version
javac -version
```

2. Update pom.xml to match your Java version:
```xml
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
```

3. Set JAVA_HOME environment variable

---

## Test Execution Issues

### Issue: Tests not running

**Symptoms:**
- `mvn test` shows no tests executed
- IDE doesn't recognize tests

**Solutions:**

1. Verify TestNG dependency in pom.xml
2. Check test class naming (must end with Test or have @Test annotation)
3. Verify testng.xml configuration
4. Check test groups in testng.xml

### Issue: All tests failing with connection errors

**Symptoms:**
- "Connection refused" errors
- Timeout exceptions

**Solutions:**

1. Check internet connectivity
2. Verify API endpoint URLs are correct
3. Check if API is accessible (try in browser/Postman)
4. Check firewall/proxy settings
5. Increase timeout:
```java
given()
    .config(RestAssured.config()
        .httpClient(HttpClientConfig.httpClientConfig()
            .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000)
            .setParam(CoreConnectionPNames.SO_TIMEOUT, 10000)))
```

---

## REST Assured Issues

### Issue: NullPointerException in response

**Symptoms:**
```
java.lang.NullPointerException
    at response.path("field")
```

**Solutions:**

1. Check if response body exists:
```java
Response response = given().get("/endpoint");
System.out.println(response.asString());
```

2. Validate response before extracting:
```java
given()
    .get("/users/1")
.then()
    .statusCode(200)
    .body("name", notNullValue());
```

3. Use safe extraction:
```java
String name = response.path("name");
if (name != null) {
    // Use name
}
```

### Issue: JSON path extraction fails

**Symptoms:**
- Cannot extract nested values
- Gets wrong values

**Solutions:**

1. Use correct JSON path syntax:
```java
// Correct
.body("data[0].id", equalTo(1))
.body("user.address.city", equalTo("New York"))

// Wrong
.body("data.0.id", equalTo(1))
```

2. Print response to verify structure:
```java
response.prettyPrint();
```

---

## Maven Issues

### Issue: Plugin execution errors

**Symptoms:**
- "Plugin not found" errors
- Maven build fails

**Solutions:**

1. Add plugin repository to pom.xml:
```xml
<pluginRepositories>
    <pluginRepository>
        <id>central</id>
        <url>https://repo.maven.apache.org/maven2</url>
    </pluginRepository>
</pluginRepositories>
```

2. Update Maven to latest version:
```bash
mvn -version
```

### Issue: Surefire plugin not finding tests

**Solutions:**

Update surefire plugin configuration:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.3</version>
    <configuration>
        <suiteXmlFiles>
            <suiteXmlFile>testng.xml</suiteXmlFile>
        </suiteXmlFiles>
    </configuration>
</plugin>
```

---

## TestNG Issues

### Issue: Tests running in wrong order

**Solutions:**

1. Use priority:
```java
@Test(priority = 1)
public void firstTest() { }

@Test(priority = 2)
public void secondTest() { }
```

2. Use dependsOnMethods:
```java
@Test
public void createUser() { }

@Test(dependsOnMethods = "createUser")
public void updateUser() { }
```

### Issue: Data provider not working

**Symptoms:**
- Test skipped
- "Data provider mismatch" error

**Solutions:**

1. Verify method signature:
```java
@DataProvider(name = "testData")
public Object[][] provideData() {
    return new Object[][] {
        {"value1", "value2"},
        {"value3", "value4"}
    };
}

@Test(dataProvider = "testData")
public void test(String param1, String param2) {
    // Test logic
}
```

---

## JSON/XML Issues

### Issue: Serialization fails

**Symptoms:**
- Cannot convert POJO to JSON
- "No serializer found" error

**Solutions:**

1. Add Jackson dependency
2. Add getters/setters to POJO or use Lombok
3. Use @JsonProperty for field mapping:
```java
@JsonProperty("first_name")
private String firstName;
```

### Issue: Deserialization fails

**Solutions:**

1. Add no-args constructor:
```java
@NoArgsConstructor
@AllArgsConstructor
public class User { }
```

2. Check field names match JSON
3. Use @JsonIgnoreProperties for unknown fields:
```java
@JsonIgnoreProperties(ignoreUnknown = true)
public class User { }
```

---

## Network Issues

### Issue: SSL/TLS errors

**Symptoms:**
- "SSL handshake failed"
- Certificate errors

**Solutions:**

```java
// Disable SSL validation (ONLY for testing!)
given()
    .relaxedHTTPSValidation()
.when()
    .get("https://endpoint");
```

### Issue: Proxy configuration

**Solutions:**

```java
given()
    .proxy("proxy.example.com", 8080)
.when()
    .get("/endpoint");
```

---

## CI/CD Issues

### Issue: Tests pass locally but fail in CI

**Solutions:**

1. Check environment variables
2. Verify network access in CI environment
3. Check Java version in CI
4. Add retry logic for flaky tests
5. Increase timeouts in CI

### Issue: Allure report not generating

**Solutions:**

1. Verify Allure plugin in pom.xml
2. Check allure-results directory exists
3. Run:
```bash
mvn clean test
mvn allure:report
mvn allure:serve
```

---

## Getting Help

If you can't find a solution:

1. Check [GitHub Issues](https://github.com/beingtush/rest-assured-practice/issues)
2. Review [REST Assured Documentation](https://rest-assured.io/)
3. Open a new issue with:
   - Error message
   - Steps to reproduce
   - Environment details
   - Code snippet

---

**Need more help? Contact: beingtush@gmail.com**
