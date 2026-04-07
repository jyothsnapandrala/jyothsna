package com.java.page;

import org.openqa.selenium.WebDriver;

import com.java.driver.SetupDriver;



public class BasePage {
	protected WebDriver driver;

	public BasePage() {
		this.driver = SetupDriver.getDriver();
	}

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
}