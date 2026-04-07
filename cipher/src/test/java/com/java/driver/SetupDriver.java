package com.java.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetupDriver {
	private static WebDriver driver;
	 
    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\PJYOTHSN\\OneDrive - Capgemini\\Documents\\Selenium\\141\\chromedriver-win64\\\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }
 
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }}
  //System.setProperty("webdriver.chrome.driver", "C:\\Users\\PJYOTHSN\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");