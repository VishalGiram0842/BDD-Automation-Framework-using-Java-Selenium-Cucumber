package com.bdd.automation.steps;

import com.bdd.automation.driver.DriverManager;
import com.bdd.automation.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

public class LoginSteps {
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private WebDriver driver;
    private LoginPage loginPage;

    /**
     * Step: User navigates to login page
     */
    @Given("User navigates to the login page")
    public void user_navigates_to_login_page() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        DriverManager.navigateToURL("https://example.com/login");
        loginPage.waitForPageLoad();
        logger.info("User navigated to login page");
    }

    /**
     * Step: User enters credentials
     */
    @When("User enters username as {string} and password as {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.login(username, password);
        logger.info("User entered username: " + username);
    }

    /**
     * Step: User clicks login button
     */
    @When("User clicks on the login button")
    public void user_clicks_login_button() {
        loginPage.clickLoginButton();
        logger.info("User clicked login button");
    }

    /**
     * Step: Verify successful login
     */
    @Then("User should be logged in successfully")
    public void user_logged_in_successfully() {
        String pageTitle = driver.getTitle();
        assertTrue("User failed to login", pageTitle.contains("Dashboard"));
        logger.info("User successfully logged in");
    }

    /**
     * Step: Verify login failure
     */
    @Then("User should see error message")
    public void user_sees_error_message() {
        String errorMsg = loginPage.getErrorMessage();
        assertNotNull("Error message not displayed", errorMsg);
        logger.info("Error message displayed: " + errorMsg);
    }
}
