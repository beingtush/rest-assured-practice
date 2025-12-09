# RestAssured Practice Project - Setup Verification Script

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "RestAssured Practice - Setup Verification" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check Java
Write-Host "1. Checking Java Installation..." -ForegroundColor Yellow
try {
    $javaVersion = java -version 2>&1 | Select-Object -First 1
    Write-Host "   OK Java is installed: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "   X Java is NOT installed" -ForegroundColor Red
    Write-Host "   Please install Java 11+ from https://adoptium.net/" -ForegroundColor Red
}

Write-Host ""

# Check Maven
Write-Host "2. Checking Maven Installation..." -ForegroundColor Yellow
try {
    $mvnVersion = mvn --version 2>&1 | Select-Object -First 1
    Write-Host "   OK Maven is installed: $mvnVersion" -ForegroundColor Green
    $mavenInstalled = $true
} catch {
    Write-Host "   X Maven is NOT installed" -ForegroundColor Red
    Write-Host "   You can either:" -ForegroundColor Yellow
    Write-Host "   - Install Maven from https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    Write-Host "   - Use an IDE (IntelliJ/Eclipse) which has Maven built-in" -ForegroundColor Yellow
    $mavenInstalled = $false
}

Write-Host ""

# Check Project Structure
Write-Host "3. Verifying Project Structure..." -ForegroundColor Yellow

$requiredPaths = @(
    "pom.xml",
    "testng.xml",
    "src/main/java/com/restassured/practice/models",
    "src/test/java/com/restassured/practice/tests",
    "src/test/resources/schemas"
)

$allPathsExist = $true
foreach ($path in $requiredPaths) {
    if (Test-Path $path) {
        Write-Host "   OK $path" -ForegroundColor Green
    } else {
        Write-Host "   X $path (MISSING)" -ForegroundColor Red
        $allPathsExist = $false
    }
}

Write-Host ""

# Count Test Files
Write-Host "4. Counting Test Classes..." -ForegroundColor Yellow
$testFiles = Get-ChildItem -Path "src/test/java/com/restassured/practice/tests" -Filter "*.java" -File
Write-Host "   OK Found $($testFiles.Count) test classes" -ForegroundColor Green
foreach ($file in $testFiles) {
    Write-Host "      - $($file.Name)" -ForegroundColor Cyan
}

Write-Host ""

# Count Model Files
Write-Host "5. Counting Model Classes..." -ForegroundColor Yellow
$modelFiles = Get-ChildItem -Path "src/main/java/com/restassured/practice/models" -Filter "*.java" -File
Write-Host "   OK Found $($modelFiles.Count) model classes" -ForegroundColor Green
foreach ($file in $modelFiles) {
    Write-Host "      - $($file.Name)" -ForegroundColor Cyan
}

Write-Host ""

# Count Schema Files
Write-Host "6. Counting JSON Schema Files..." -ForegroundColor Yellow
$schemaFiles = Get-ChildItem -Path "src/test/resources/schemas" -Filter "*.json" -File
Write-Host "   OK Found $($schemaFiles.Count) schema files" -ForegroundColor Green
foreach ($file in $schemaFiles) {
    Write-Host "      - $($file.Name)" -ForegroundColor Cyan
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Summary" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

if ($allPathsExist) {
    Write-Host "OK Project structure is complete" -ForegroundColor Green
} else {
    Write-Host "X Project structure has missing files" -ForegroundColor Red
}

Write-Host ""

if ($mavenInstalled) {
    Write-Host "Next Steps:" -ForegroundColor Yellow
    Write-Host "1. Run: mvn clean install" -ForegroundColor Cyan
    Write-Host "2. Run tests: mvn test" -ForegroundColor Cyan
    Write-Host "3. Or open in IDE and run tests from there" -ForegroundColor Cyan
} else {
    Write-Host "Recommended Next Steps:" -ForegroundColor Yellow
    Write-Host "1. Install IntelliJ IDEA Community Edition (Free)" -ForegroundColor Cyan
    Write-Host "   Download from: https://www.jetbrains.com/idea/download/" -ForegroundColor Cyan
    Write-Host "2. Open this folder in IntelliJ" -ForegroundColor Cyan
    Write-Host "3. Wait for dependencies to download" -ForegroundColor Cyan
    Write-Host "4. Right-click any test class and select Run" -ForegroundColor Cyan
}

Write-Host ""
Write-Host "For detailed setup instructions, see SETUP_GUIDE.md" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
