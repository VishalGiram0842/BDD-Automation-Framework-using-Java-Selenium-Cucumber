# BDD Automation Framework using Java, Selenium, Cucumber & Maven

A comprehensive Behavior-Driven Development (BDD) automation framework built with Java, Selenium WebDriver, Cucumber, and Maven for behavior-driven testing of web applications.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Writing Tests](#writing-tests)
- [Running Tests](#running-tests)
- [Report Generation](#report-generation)
- [Best Practices](#best-practices)
- [Contributing](#contributing)

## Overview

This framework combines the power of Behavior-Driven Development (BDD) with modern automation testing practices. It uses Cucumber for writing test scenarios in a human-readable format and Selenium WebDriver for web UI automation.

**Key Approach:**
- Write test scenarios in Gherkin language (Given-When-Then format)
- Implement step definitions using Page Object Model (POM)
- Execute tests in parallel using Maven and JUnit
- Generate comprehensive HTML reports
- Seamless CI/CD integration

## Features

✅ **Gherkin Test Scenarios** - Write tests in plain English using Gherkin syntax  
✅ **Page Object Model (POM)** - Structured and maintainable test design pattern  
✅ **Cross-Browser Testing** - Support for Chrome, Firefox, Edge, and Safari  
✅ **Parallel Test Execution** - Leverage Maven and JUnit for parallel execution  
✅ **Comprehensive Reporting** - Cucumber HTML reports and TestNG integration  
✅ **Screenshot & Video Capture** - Automatic capture on test failures  
✅ **Data-Driven Testing** - Support for scenario outlines and multiple test data sets  
✅ **Logging & Monitoring** - Detailed logs for debugging and monitoring  
✅ **CI/CD Ready** - Jenkins integration support  
✅ **Wait Strategies** - Implicit and explicit waits for reliable test execution  
✅ **Hooks Support** - Before and after hooks for test setup and teardown  
✅ **Configuration Management** - External configuration files for environments  

## Tech Stack

| Component | Version | Purpose |
|-----------|---------|----------|
| **Java** | 11+ | Programming Language |
| **Selenium WebDriver** | 4.x | Web UI Automation |
| **Cucumber** | 7.x | BDD Test Framework |
| **Maven** | 3.6+ | Build & Dependency Management |
| **JUnit** | 4.x/5.x | Test Execution |
| **Log4j** | 2.x | Logging |
| **TestNG** | 7.x | Advanced Test Features |

## Prerequisites

- **Java JDK 11 or higher**
- **Maven 3.6 or higher**
- **Git** - Version control
- **IDE** - Eclipse, IntelliJ IDEA, or Visual Studio Code
- **WebDriver** - ChromeDriver, GeckoDriver, EdgeDriver (based on browser choice)

## Project Structure

```
BDD-Automation-Framework-using-Java-Selenium-Cucumber/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/              # Page Object classes
│   │   │   ├── steps/              # Step Definition classes
│   │   │   ├── hooks/              # Before/After hooks
│   │   │   ├── utils/              # Utility classes
│   │   │   ├── driver/             # WebDriver management
│   │   │   └── constants/          # Constants and enums
│   │   └── resources/
│   │       ├── config/             # Configuration files
│   │       └── features/           # Feature files
│   └── test/
│       ├── java/
│       │   └── runners/            # Test runners
│       └── resources/
│           ├── features/           # Feature files
│           ├── cucumber.properties # Cucumber settings
│           └── log4j2.properties   # Logging config
├── target/                         # Build output
├── reports/                        # Test reports
├── pom.xml                         # Maven configuration
├── .gitignore                      # Git ignore rules
└── README.md                       # This file
```

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/VishalGiram0842/BDD-Automation-Framework-using-Java-Selenium-Cucumber.git
   cd BDD-Automation-Framework-using-Java-Selenium-Cucumber
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Download WebDrivers**
   - Use WebDriverManager or manually download appropriate WebDriver binaries for your OS
   - Update driver paths in configuration files

## Configuration

Create a `config.properties` file in `src/main/resources/config/`:

```properties
# Browser Configuration
browser=chrome
headless=false

# Application URLs
base.url=https://www.example.com

# Timeouts (in seconds)
implicit.wait=10
explicit.wait=20
page.load.timeout=30

# Logging
log.level=info

# Screenshots
screenshot.on.failure=true
video.recording=false

# Parallel Execution
parallel.threads=4
```

## Writing Tests

### 1. Create a Feature File

**features/login.feature**
```gherkin
Feature: User Login Functionality
  As a user
  I want to login to the application
  So that I can access protected features

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "testuser"
    And I enter password "password123"
    And I click the login button
    Then I should see the dashboard

  Scenario Outline: Login with multiple credentials
    Given I am on the login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should see <result>

    Examples:
      | username | password   | result      |
      | user1    | pass123    | dashboard   |
      | user2    | pass456    | error_page  |
```

### 2. Create a Page Object

**pages/LoginPage.java**
```java
public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login_btn");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
```

### 3. Create Step Definitions

**steps/LoginSteps.java**
```java
public class LoginSteps {
    private WebDriver driver = Hooks.driver;
    private LoginPage loginPage = new LoginPage(driver);

    @Given("I am on the login page")
    public void user_on_login_page() {
        driver.get("https://example.com/login");
    }

    @When("I enter username \\"([^\\"]*)\\"") 
    public void user_enters_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password \\"([^\\"]*)\\"") 
    public void user_enters_password(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click the login button")
    public void user_clicks_login() {
        loginPage.clickLogin();
    }

    @Then("I should see the dashboard")
    public void user_sees_dashboard() {
        assertTrue(driver.getTitle().contains("Dashboard"));
    }
}
```

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Feature File
```bash
mvn clean test -Dcucumber.filter.name="Login"
```

### Run Specific Tag
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

### Run Tests in Parallel
```bash
mvn clean test -Dthreads=4
```

### Run Tests on Specific Browser
```bash
mvn clean test -Dbrowser=firefox
```

## Report Generation

Reports are automatically generated after test execution:

### Cucumber HTML Reports
```
target/cucumber-reports/cucumber.html
```

### TestNG Reports
```
test-output/index.html
```

## Best Practices

1. **Gherkin Scenarios** - Write clear, concise, and readable test scenarios
2. **Page Object Model** - Maintain separate page classes for each page
3. **DRY Principle** - Don't Repeat Yourself - reuse common functionality
4. **Meaningful Names** - Use descriptive names for steps, methods, and variables
5. **Single Responsibility** - Each step should verify one behavior
6. **Data Management** - Keep test data separate from test logic
7. **Wait Strategies** - Use explicit waits instead of hard sleeps
8. **Error Handling** - Implement proper exception handling
9. **Logging** - Add appropriate logs for debugging
10. **Code Reviews** - Review and refactor code regularly
11. **Documentation** - Document complex scenarios and utilities
12. **CI/CD Integration** - Automate test execution in pipeline

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Write feature files for your changes
4. Commit your changes (`git commit -m 'Add AmazingFeature'`)
5. Push to the branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

## Author

**Vishal Giram** - [GitHub Profile](https://github.com/VishalGiram0842)

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues, questions, or suggestions, please open an issue on the GitHub repository.

---

**Happy Testing! 🚀**
