package com.runner;
 
 
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
 
@CucumberOptions(features = "src/test/resources/Features/Profile.feature",
glue="com.stepdefinition",
plugin= {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
	
 
public class TestRunnerExtenedTestNG extends AbstractTestNGCucumberTests {
 
}
