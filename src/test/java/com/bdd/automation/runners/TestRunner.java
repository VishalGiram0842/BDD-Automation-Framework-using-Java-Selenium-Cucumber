package com.bdd.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Test Runner class for executing Cucumber scenarios
 * Uses TestNG as the testing framework
 */
@CucumberOptions(
    features = "src/main/resources/features",
    glue = {"com.bdd.automation.steps", "com.bdd.automation.hooks"},
    plugin = {
        "pretty",
        "json:target/cucumber.json",
        "html:target/cucumber-reports.html"
    },
    monochrome = false,
    dryRun = false,
    publish = false,
    tags = "@sanity or @smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Provide scenarios for parallel execution
     * Each scenario will be executed in a separate thread
     */
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
