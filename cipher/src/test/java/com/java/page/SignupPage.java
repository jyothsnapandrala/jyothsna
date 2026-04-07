package com.java.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

	private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
	/*
	 * public SignupPage(WebDriver driver) { this.driver = driver; }
	 */
    public SignupPage(WebDriver driver) {
		this.driver = driver;
this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
// TODO Auto-generated constructor stub
}
    public void goToLoginPage( String url) {
        driver.get(url);
       
    }
    // Locators
    private By emailField = By.id("email_signup");
    private By loginRegisterButton = By.id("btnlogin");
    private By otpField = By.cssSelector(".form-control.text-center.store-otp-input");
    private By passwordField = By.id("password_signup");
    private By confirmPasswordField = By.id("confirm_password_signup");
    private By submitButton = By.id("Submit");

    // Actions
	/*
	 * public void enterEmail(String[] email) {
	 * driver.findElement(emailField).sendKeys(email); }
	 */

    public void clickLoginRegister() {
    	WebElement signInElement = driver.findElement(loginRegisterButton);
		
    	signInElement.click();
    }

    public void enterOTP() {
        driver.findElement(otpField);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

	public void enterEmail(String email) {
		driver.findElement(emailField).sendKeys(email); 
		
	}
}
