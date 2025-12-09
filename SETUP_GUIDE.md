# Setup Guide for RestAssured Practice Project

## Prerequisites Installation

### 1. Install Java (JDK 11 or higher)

**If Java is not installed:**

1. Download JDK from: https://www.oracle.com/java/technologies/downloads/
   - Or use OpenJDK: https://adoptium.net/
2. Install JDK 11 or higher
3. Set JAVA_HOME environment variable:
   - Windows: `setx JAVA_HOME "C:\Program Files\Java\jdk-11"`
   - Add to PATH: `setx PATH "%PATH%;%JAVA_HOME%\bin"`

**Verify Java Installation:**
```powershell
java -version
```

### 2. Install Apache Maven

**Download and Install:**

1. Download Maven from: https://maven.apache.org/download.cgi
   - Download the Binary zip archive (e.g., apache-maven-3.9.x-bin.zip)

2. Extract to a folder (e.g., `C:\Program Files\Apache\maven`)

3. Set environment variables:
   ```powershell
   # Set MAVEN_HOME
   setx MAVEN_HOME "C:\Program Files\Apache\maven"
   
   # Add to PATH
   setx PATH "%PATH%;%MAVEN_HOME%\bin"
   ```

4. Close and reopen PowerShell/Command Prompt

**Verify Maven Installation:**
```powershell
mvn --version
```

## Alternative: Use IDE (Recommended for Beginners)

If you prefer not to use command line, you can use an IDE that has built-in Maven support:

### Option 1: IntelliJ IDEA (Recommended)

1. Download IntelliJ IDEA Community Edition (Free): https://www.jetbrains.com/idea/download/
2. Install IntelliJ IDEA
3. Open the project:
   - File → Open → Select "RestAssured Practice" folder
4. IntelliJ will automatically detect the Maven project and download dependencies
5. Right-click on any test class → Run

### Option 2: Eclipse

1. Download Eclipse IDE for Java Developers: https://www.eclipse.org/downloads/
2. Install Eclipse
3. Import the project:
   - File → Import → Maven → Existing Maven Projects
   - Browse to "RestAssured Practice" folder
4. Eclipse will download dependencies automatically
5. Right-click on test class → Run As → TestNG Test

### Option 3: Visual Studio Code

1. Install VS Code: https://code.visualstudio.com/
2. Install extensions:
   - Java Extension Pack
   - Maven for Java
   - TestNG extension
3. Open "RestAssured Practice" folder in VS Code
4. VS Code will prompt to sync Maven dependencies

## Building the Project

### Using Command Line (Maven):

```powershell
# Navigate to project directory
cd "RestAssured Practice"

# Clean and install dependencies
mvn clean install

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=BasicGetRequestTest

# Run with TestNG XML
mvn test -DsuiteXmlFile=testng.xml
```

### Using IDE:

1. **IntelliJ IDEA:**
   - Right-click on `testng.xml` → Run
   - Or right-click on any test class → Run

2. **Eclipse:**
   - Right-click on test class → Run As → TestNG Test
   - Or right-click on `testng.xml` → Run As → TestNG Suite

## Troubleshooting

### Issue: Dependencies not downloading

**Solution:**
```powershell
# Delete .m2 repository cache and rebuild
rmdir /s "%USERPROFILE%\.m2\repository"
mvn clean install -U
```

### Issue: Lombok not working

**For IntelliJ:**
1. Install Lombok plugin: File → Settings → Plugins → Search "Lombok" → Install
2. Enable annotation processing: Settings → Build → Compiler → Annotation Processors → Enable

**For Eclipse:**
1. Download lombok.jar from: https://projectlombok.org/download
2. Run: `java -jar lombok.jar`
3. Select Eclipse installation and click Install

### Issue: Tests failing due to network/API issues

Some tests may fail if:
- Public APIs are down or rate-limited
- No internet connection
- Firewall blocking requests

This is expected behavior as the project uses real public APIs.

## Quick Start (No Installation Required)

If you want to quickly try the project without installing Maven:

1. Install IntelliJ IDEA Community Edition (has Maven built-in)
2. Open the "RestAssured Practice" folder
3. Wait for IntelliJ to download dependencies (check bottom status bar)
4. Open any test class in `src/test/java/com/restassured/practice/tests/`
5. Right-click on the test class → Run

## Next Steps

Once setup is complete:

1. Read the `README.md` file for project overview
2. Start with `BasicGetRequestTest.java` to understand the basics
3. Explore other test classes to learn different concepts
4. Modify tests and experiment with different scenarios
5. Create your own test classes to practice

## Resources

- RestAssured Documentation: https://rest-assured.io/
- TestNG Documentation: https://testng.org/
- JSONPlaceholder API: https://jsonplaceholder.typicode.com/
- ReqRes API: https://reqres.in/

Happy Testing! 🚀
