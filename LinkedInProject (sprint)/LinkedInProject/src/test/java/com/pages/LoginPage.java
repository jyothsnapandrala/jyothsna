package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;

	// Locators 
	private By emailField = By.id("username");
	private By passwordField = By.id("password");
	private By signInButton = By
			.xpath("//button[contains(@class, 'btn__primary--large') and contains(@class, 'from__button--floating')]");

	public LoginPage(WebDriver driver) {
//    	System.out.println(driver2);
		this.driver = driver;
 
	}

	public void enterUsername(String username) {

		WebElement emailElement = driver.findElement(emailField);
		emailElement.clear();
		emailElement.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement passwordElement = driver.findElement(passwordField);
		passwordElement.clear();
		passwordElement.sendKeys(password);
	}

	public void clickSignInButton() {
		WebElement signInElement = driver.findElement(signInButton);
		signInElement.click();
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickSignInButton();
	}

}