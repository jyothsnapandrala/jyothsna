package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features/Profile.feature", glue = {"com.stepdefinition","com.setup"},plugin = {
        // Prints the Gherkin steps in the console
"html:reportsnew/cucumber-reports.html"})
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {
	
	
}
