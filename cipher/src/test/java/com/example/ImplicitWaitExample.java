package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
 
public class ImplicitWaitExample {
    public static void main(String[] args) {
 
        WebDriver driver = new ChromeDriver();
        
        // Apply Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
 
        driver.get("https://www.google.com");
 
        // This element will wait up to 10 seconds before throwing NoSuchElementException
        driver.findElement(By.name("q")).sendKeys("Selenium implicit wait example");
 
        driver.quit();
    }}