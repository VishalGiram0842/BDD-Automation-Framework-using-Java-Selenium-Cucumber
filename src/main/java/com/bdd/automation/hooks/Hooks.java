package com.bdd.automation.hooks;

import com.bdd.automation.driver.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private WebDriver driver;

    /**
     * Before hook - Runs before each scenario
     * Initializes WebDriver and logs scenario start
     */
    @Before
    public void setUp(Scenario scenario) {
        logger.info("=====================================");
        logger.info("Starting Scenario: " + scenario.getName());
        logger.info("=====================================");

        // Initialize WebDriver
        driver = DriverManager.initializeDriver("chrome");
        logger.info("WebDriver initialized successfully");
    }

    /**
     * After hook - Runs after each scenario
     * Closes WebDriver and logs scenario result
     */
    @After
    public void tearDown(Scenario scenario) {
        logger.info("=====================================");
        if (scenario.isFailed()) {
            logger.error("Scenario FAILED: " + scenario.getName());
        } else {
            logger.info("Scenario PASSED: " + scenario.getName());
        }
        logger.info("=====================================");

        // Close WebDriver
        DriverManager.quitDriver();
        logger.info("WebDriver closed successfully");
    }
}
