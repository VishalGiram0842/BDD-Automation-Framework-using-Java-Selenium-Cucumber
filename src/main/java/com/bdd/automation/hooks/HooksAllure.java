package com.bdd.automation.hooks;

import com.bdd.automation.driver.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class HooksAllure {
    private static final Logger logger = LogManager.getLogger(HooksAllure.class);
    private WebDriver driver;

    /**
     * Before hook - Runs before each scenario
     * Initializes WebDriver and logs scenario start with Allure
     */
    @Before
    @Step("Setting up test environment")
    public void setUp(Scenario scenario) {
        logger.info("=====================================");
        logger.info("Starting Scenario: " + scenario.getName());
        logger.info("=====================================");

        // Initialize WebDriver
        driver = DriverManager.initializeDriver("chrome");
        logger.info("WebDriver initialized successfully");

        // Allure reporting
        Allure.feature("BDD Automation Framework");
        Allure.story(scenario.getName());
        Allure.label("severity", "critical");
        Allure.description("Test Scenario: " + scenario.getName());
    }

    /**
     * After hook - Runs after each scenario
     * Closes WebDriver, captures screenshot on failure, and reports to Allure
     */
    @After
    @Step("Tearing down test environment")
    public void tearDown(Scenario scenario) {
        logger.info("=====================================");
        
        if (scenario.isFailed()) {
            logger.error("Scenario FAILED: " + scenario.getName());
            captureScreenshot(scenario);
            Allure.addAttachment("Failure Screenshot", "image/png",
                    new java.io.ByteArrayInputStream(
                            ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)),
                    "png");
        } else {
            logger.info("Scenario PASSED: " + scenario.getName());
        }
        logger.info("=====================================");

        // Close WebDriver
        DriverManager.quitDriver();
        logger.info("WebDriver closed successfully");
    }

    /**
     * Capture screenshot on test failure
     */
    private void captureScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Screenshot on Failure - " + scenario.getName(),
                    "image/png",
                    new java.io.ByteArrayInputStream(screenshot),
                    "png"
            );
            logger.info("Screenshot captured for failed scenario: " + scenario.getName());
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
