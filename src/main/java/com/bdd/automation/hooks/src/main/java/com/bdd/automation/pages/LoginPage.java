package com.bdd.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Page Elements
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "loginBtn")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='error-message']")
    private WebElement errorMessage;

    /**
     * Constructor - Initialize the page
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for page to load
     */
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
    }

    /**
     * Enter username
     */
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Get error message
     */
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    /**
     * Login with credentials
     */
    public void login(String username, String password) {
        waitForPageLoad();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
