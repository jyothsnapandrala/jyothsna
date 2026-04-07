package com.java.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.java.stepdef",
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true,
    tags="@onlinebrandstore"
)
public class TestRunner extends AbstractTestNGCucumberTests {
	public static void tearDown() {
        com.java.driver.SetupDriver.quitDriver();
        System.out.println("Teardown: All tests completed.");
    }
}
