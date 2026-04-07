package com.java.onlinestore.login;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Javasscriptexceutor {
	public static void main(String[] args) throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\PJYOTHSN\\OneDrive - Capgemini\\Documents\\Selenium\\141\\chromedriver-win64\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://servicecentral.capgemini.com/sc");
		JavascriptExecutor j = (JavascriptExecutor) driver;
		//To scroll down
		//System.out.println("Scrolling down");
		/*
		 * j.executeScript("window.scrollBy(0,50)"); Thread.sleep(3000);
		 */
		//To scroll up
		System.out.println("Scrolling viewelemnt");
		/* j.executeScript("window.scrollBy(0,-500)"); */
		j.executeScript("arguments[0].scrollIntoView(true);",driver.findElement( By.xpath("//a[@class='titre ng-binding']"))); 
		
		}
		


}
