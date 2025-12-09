# Quick Start Guide - RestAssured Practice

## ✅ Project Setup Complete!

Your RestAssured practice project has been successfully created with:
- ✅ 10 comprehensive test classes
- ✅ 3 POJO model classes
- ✅ 5 JSON schema files
- ✅ Complete Maven configuration
- ✅ TestNG configuration

## 🚀 Two Ways to Get Started

### Option 1: Using IDE (Easiest - Recommended)

#### IntelliJ IDEA (Recommended)

1. **Download & Install IntelliJ IDEA Community Edition (Free)**
   - Download: https://www.jetbrains.com/idea/download/
   - Install and launch IntelliJ IDEA

2. **Open the Project**
   - Click "Open" on the welcome screen
   - Navigate to and select the `RestAssured Practice` folder
   - Click "OK"

3. **Wait for Dependencies**
   - IntelliJ will automatically detect the Maven project
   - Look at the bottom status bar - it will show "Importing..."
   - Wait until it says "Build completed successfully" (may take 2-5 minutes)

4. **Run Your First Test**
   - Navigate to: `src/test/java/com/restassured/practice/tests/BasicGetRequestTest.java`
   - Right-click on the file → Select "Run BasicGetRequestTest"
   - You'll see test results in the bottom panel

5. **Run All Tests**
   - Right-click on `testng.xml` in the project root
   - Select "Run testng.xml"

#### Eclipse IDE

1. **Download & Install Eclipse**
   - Download: https://www.eclipse.org/downloads/
   - Choose "Eclipse IDE for Java Developers"

2. **Import the Project**
   - File → Import → Maven → Existing Maven Projects
   - Browse to `RestAssured Practice` folder
   - Click "Finish"

3. **Wait for Maven Build**
   - Eclipse will automatically download dependencies
   - Check progress in bottom-right corner

4. **Run Tests**
   - Right-click on any test class
   - Run As → TestNG Test

#### Visual Studio Code

1. **Install VS Code**
   - Download: https://code.visualstudio.com/

2. **Install Required Extensions**
   - Open VS Code
   - Go to Extensions (Ctrl+Shift+X)
   - Install:
     - "Extension Pack for Java" by Microsoft
     - "Maven for Java" by Microsoft
     - "TestNG Runner" 

3. **Open Project**
   - File → Open Folder → Select `RestAssured Practice`
   - VS Code will prompt to sync Maven project

4. **Run Tests**
   - Open any test file
   - Click the green play button next to test methods

---

### Option 2: Using Command Line (Requires Maven Installation)

#### Install Maven First

**Windows:**
1. Download Maven: https://maven.apache.org/download.cgi
2. Extract to `C:\Program Files\Apache\maven`
3. Add to System Environment Variables:
   ```
   Variable: MAVEN_HOME
   Value: C:\Program Files\Apache\maven
   
   Variable: PATH
   Add: %MAVEN_HOME%\bin
   ```
4. Restart PowerShell/CMD

**Verify Installation:**
```powershell
mvn --version
```

#### Build and Run Tests

```powershell
# Navigate to project directory
cd "RestAssured Practice"

# Download dependencies and build project
mvn clean install

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=BasicGetRequestTest

# Run tests with TestNG XML
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📚 Learning Path

### Start Here (Beginner):

1. **BasicGetRequestTest.java** - Learn GET requests, response validation
2. **PostRequestTest.java** - Learn POST requests, request body handling
3. **QueryParametersTest.java** - Learn query parameters, filtering
4. **PathParametersTest.java** - Learn path parameters, dynamic URLs

### Intermediate:

5. **PutPatchDeleteTest.java** - Learn update and delete operations
6. **HeadersAndCookiesTest.java** - Learn custom headers, cookies
7. **AuthenticationTest.java** - Learn various authentication methods

### Advanced:

8. **SerializationDeserializationTest.java** - Learn POJO handling
9. **JsonSchemaValidationTest.java** - Learn schema validation
10. **FileUploadDownloadTest.java** - Learn file operations

---

## 🎯 What Each Test Class Covers

| Test Class | Topics Covered | Key Concepts |
|------------|---------------|--------------|
| **BasicGetRequestTest** | GET requests, Response validation | Status codes, JSON path, Response time |
| **PostRequestTest** | POST requests, Request bodies | JSON, HashMap, POJO serialization |
| **PutPatchDeleteTest** | Update/Delete operations | PUT vs PATCH, Resource manipulation |
| **QueryParametersTest** | Query parameters | Filtering, Pagination, Multiple params |
| **PathParametersTest** | Path parameters | Dynamic URLs, Variable substitution |
| **HeadersAndCookiesTest** | Headers & Cookies | Custom headers, Cookie handling |
| **AuthenticationTest** | Authentication methods | Basic, Bearer, API Key, Digest auth |
| **JsonSchemaValidationTest** | Schema validation | JSON schema, Structure verification |
| **SerializationDeserializationTest** | POJO conversion | JSON ↔ POJO, Complex objects |
| **FileUploadDownloadTest** | File operations | Multipart, Binary data, Downloads |

---

## 🌐 Public APIs Used

All tests use **free public APIs** that don't require signup:

- **JSONPlaceholder** (https://jsonplaceholder.typicode.com/) - Fake REST API for testing
- **ReqRes** (https://reqres.in/) - REST API for testing and prototyping
- **HTTPBin** (https://httpbin.org/) - HTTP request & response service
- **REST Countries** (https://restcountries.com/) - Country information API

---

## 💡 Tips for Practice

1. **Start Small**: Run one test class at a time
2. **Read the Code**: Each test is well-commented
3. **Modify & Experiment**: Change values, add assertions, try different scenarios
4. **Check Logs**: Use `.log().all()` to see request/response details
5. **Create Your Own**: Add new test methods to practice
6. **Handle Failures**: Some tests may fail if APIs are down - this is normal!

---

## 🔧 Troubleshooting

### Tests are failing
- **Check internet connection** - All tests use online APIs
- **APIs might be down** - Try again later
- **Rate limiting** - Some APIs limit requests

### Dependencies not downloading
- **Check internet connection**
- **Clear Maven cache**: Delete `%USERPROFILE%\.m2\repository` folder
- **Re-import project** in your IDE

### Lombok not working in IDE
- **IntelliJ**: Install Lombok plugin + Enable annotation processing
- **Eclipse**: Download and run lombok.jar installer

---

## 📖 Additional Resources

- **RestAssured Docs**: https://rest-assured.io/
- **TestNG Docs**: https://testng.org/doc/
- **My GitHub**: Create your own repo to save your practice!

---

## ✨ What to Do Next

1. **Open the project in IntelliJ IDEA** (easiest option)
2. **Run `BasicGetRequestTest`** to see your first API test in action
3. **Read through the test code** to understand the syntax
4. **Modify a test** - change values and see what happens
5. **Create your own test** - practice makes perfect!

---

## 🎓 Learning Goals

By the end of this practice project, you should be able to:

✅ Make GET, POST, PUT, PATCH, DELETE requests  
✅ Validate status codes and response bodies  
✅ Handle query and path parameters  
✅ Work with headers and cookies  
✅ Implement authentication  
✅ Serialize/Deserialize POJOs  
✅ Validate JSON schemas  
✅ Upload and download files  
✅ Use Hamcrest matchers for assertions  
✅ Follow BDD style (Given-When-Then)  

---

## 🆘 Need Help?

- Check `README.md` for project overview
- Check `SETUP_GUIDE.md` for detailed installation instructions
- Run `verify-setup.ps1` to check your setup

**Happy Testing! 🚀**
