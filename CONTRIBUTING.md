# Contributing to REST Assured API Testing Framework

First off, thank you for considering contributing to this project! 🎉

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
- [Getting Started](#getting-started)
- [Development Workflow](#development-workflow)
- [Pull Request Process](#pull-request-process)
- [Coding Standards](#coding-standards)
- [Commit Message Guidelines](#commit-message-guidelines)

---

## Code of Conduct

This project and everyone participating in it is governed by our Code of Conduct. By participating, you are expected to uphold this code. Please be respectful and professional in all interactions.

---

## How Can I Contribute?

### 🐛 Reporting Bugs

Before creating bug reports, please check the existing issues to avoid duplicates. When creating a bug report, include:

- **Clear title and description**
- **Steps to reproduce** the issue
- **Expected behavior** vs **actual behavior**
- **Screenshots** (if applicable)
- **Environment details** (OS, Java version, Maven version)

### 💡 Suggesting Enhancements

Enhancement suggestions are welcome! Please include:

- **Clear use case** for the enhancement
- **Detailed description** of the proposed functionality
- **Why this enhancement would be useful** to most users

### 📝 Adding Test Examples

We're always looking for more test examples! You can contribute by:

- Adding tests for new public APIs
- Demonstrating advanced REST Assured features
- Adding edge case scenarios
- Improving existing test coverage

### 📖 Improving Documentation

Documentation improvements are highly valued:

- Fix typos or grammatical errors
- Add more code examples
- Improve clarity of existing documentation
- Translate documentation (if applicable)

---

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- Git
- Your favorite IDE (IntelliJ IDEA recommended)

### Fork and Clone

1. Fork the repository on GitHub
2. Clone your fork locally:

```bash
git clone https://github.com/YOUR-USERNAME/rest-assured-practice.git
cd rest-assured-practice
```

3. Add upstream remote:

```bash
git remote add upstream https://github.com/beingtush/rest-assured-practice.git
```

### Build and Test

1. Install dependencies:

```bash
mvn clean install
```

2. Run tests to ensure everything works:

```bash
mvn test
```

---

## Development Workflow

### 1. Create a Branch

Create a feature branch for your work:

```bash
git checkout -b feature/your-feature-name
```

Branch naming conventions:
- `feature/` - New features or enhancements
- `fix/` - Bug fixes
- `docs/` - Documentation changes
- `refactor/` - Code refactoring
- `test/` - Adding or updating tests

### 2. Make Your Changes

- Write clean, readable code
- Follow the existing code style
- Add tests for new functionality
- Update documentation as needed

### 3. Test Your Changes

Before committing, ensure:

```bash
# Run all tests
mvn test

# Check for compilation errors
mvn clean compile

# Verify test coverage
mvn verify
```

### 4. Commit Your Changes

```bash
git add .
git commit -m "type: brief description"
```

See [Commit Message Guidelines](#commit-message-guidelines) below.

### 5. Keep Your Branch Updated

```bash
git fetch upstream
git rebase upstream/main
```

### 6. Push to Your Fork

```bash
git push origin feature/your-feature-name
```

---

## Pull Request Process

### Before Submitting

- ✅ All tests pass locally
- ✅ Code follows project conventions
- ✅ Documentation is updated
- ✅ Commits are clean and well-organized
- ✅ Branch is up to date with main

### Submitting the PR

1. Go to your fork on GitHub
2. Click "New Pull Request"
3. Select your feature branch
4. Fill in the PR template:

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Documentation update
- [ ] Code refactoring

## Testing
Describe how you tested your changes

## Checklist
- [ ] Tests pass locally
- [ ] Code follows style guidelines
- [ ] Documentation updated
- [ ] No breaking changes
```

5. Submit the PR

### PR Review Process

- Maintainers will review your PR within a few days
- Address any feedback or requested changes
- Once approved, your PR will be merged!

---

## Coding Standards

### Java Code Style

- **Indentation**: 4 spaces (no tabs)
- **Line length**: Maximum 120 characters
- **Naming conventions**:
  - Classes: `PascalCase`
  - Methods: `camelCase`
  - Constants: `UPPER_SNAKE_CASE`
  - Variables: `camelCase`

### Test Naming

```java
@Test
public void testMethodName_Scenario_ExpectedResult() {
    // Test implementation
}
```

Examples:
- `testGetUser_WithValidId_ReturnsUser()`
- `testCreatePost_WithEmptyBody_Returns400()`

### Code Organization

```java
// 1. Imports
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

// 2. Class with JavaDoc
/**
 * Test class description
 */
public class MyTest {
    
    // 3. Test methods with Given-When-Then
    @Test
    public void testExample() {
        given()
            .baseUri("https://api.example.com")
        .when()
            .get("/endpoint")
        .then()
            .statusCode(200);
    }
}
```

### Comments

- Use JavaDoc for classes and public methods
- Comment complex logic
- Avoid obvious comments
- Keep comments up-to-date

---

## Commit Message Guidelines

We follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

### Format

```
<type>: <subject>

[optional body]

[optional footer]
```

### Types

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks
- `ci`: CI/CD changes

### Examples

```
feat: Add GraphQL API testing examples

fix: Resolve null pointer in authentication test

docs: Update README with new examples

test: Add tests for file upload functionality

refactor: Simplify request specification builder

ci: Add Java 21 to test matrix
```

### Best Practices

- Use present tense ("Add feature" not "Added feature")
- Use imperative mood ("Move cursor to..." not "Moves cursor to...")
- Keep subject line under 50 characters
- Capitalize the subject line
- Don't end subject with a period
- Separate subject from body with blank line

---

## Questions?

If you have questions, feel free to:

- Open an issue with the `question` label
- Reach out to: **beingtush@gmail.com**
- Check existing documentation

---

## Recognition

Contributors will be recognized in:

- GitHub Contributors page
- Project documentation
- Release notes (for significant contributions)

---

Thank you for contributing! Your efforts help make this project better for everyone. 🚀

**Happy Coding!** ❤️
