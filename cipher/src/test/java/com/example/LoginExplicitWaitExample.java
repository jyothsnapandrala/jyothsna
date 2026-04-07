package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
 
public class LoginExplicitWaitExample {
    public static void main(String[] args) {
 
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com/login");
 
        // Create Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 
        // Wait until username field is visible
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username"))
        );
        usernameField.sendKeys("jyothsna");
 
        // Wait until password field is visible
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );
        passwordField.sendKeys("Password123");
 
        // Wait until login button is clickable
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("loginBtn"))
        );
        loginBtn.click();
 
        // Wait until dashboard/home page is loaded (example)
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("profileIcon"))
        );
 
        System.out.println("Login successful!");
 
        driver.quit();
    }
}