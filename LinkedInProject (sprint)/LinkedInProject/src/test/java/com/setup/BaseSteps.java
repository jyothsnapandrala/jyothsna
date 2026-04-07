package com.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.parameters.ExcelReader;


public class BaseSteps {

	public static WebDriver driver;

	// static PropertiesReader propertiesReader;
	ExcelReader excelReader;

	
	public void setUp() throws Exception {

		// propertiesReader = new PropertiesReader();
		excelReader = new ExcelReader();

		// System.setProperty("webdriver.chrome.driver",
		// config.getProperty("chromeDriverPath"));

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		// driver.get(propertiesReader.getProperty("url"));
		driver.get(ExcelReader.getUrl());

	}

	
	public void tearDown() {

		if (driver != null) {

			driver.quit();

		}

	}
}