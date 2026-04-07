package com.java.onlinestore.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginpage {
	WebDriver driver;
	@BeforeMethod
	public void Setup() {
		driver= new ChromeDriver();
		driver.get("https://capgeminibrandstore.printstop.co.in/");
		 driver.manage().window().maximize();
	}
	@Test
	public void testValidLogin() throws InterruptedException {
	    driver.findElement(By.id("username")).sendKeys("validUser");
	    driver.findElement(By.id("password")).sendKeys("validPass");
	    driver.findElement(By.id("loginButton")).click();
	    Thread.sleep(2000);
	    Assert.assertTrue(driver.findElement(By.id("welcomeMessage")).isDisplayed());
	}
	// Negative Test: Invalid login
	@Test
	public void testInvalidLogin() throws InterruptedException {
	  
	    driver.findElement(By.id("")).sendKeys("invalidUser");
	    driver.findElement(By.id("password")).sendKeys("invalidPass");
	    driver.findElement(By.id("loginButton")).click();
	    Thread.sleep(2000);
	    Assert.assertTrue(driver.findElement(By.id("errorMessage")).isDisplayed());
	}
}
