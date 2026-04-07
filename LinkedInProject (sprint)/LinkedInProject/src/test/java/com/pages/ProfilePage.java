package com.pages;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.parameters.ExcelReader;
import com.parameters.PropertiesReader;

public class ProfilePage {
	WebDriver driver;
	ExcelReader excelReader = new ExcelReader();
	PropertiesReader propertiesReader;

	// Constructor to initialize WebDriver
	public ProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	// Method to view profile
	public void ViewProfile() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement profileIcon = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[starts-with(@id,'ember')]/span[text()='Me']")));
		profileIcon.click();
		WebElement viewProfileLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View Profile")));
		viewProfileLink.click();
	}
	// Scenario 1

	public void Headline(String headline) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement editIcon = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("svg.artdeco-button__icon[data-test-icon='edit-medium']")));
		editIcon.click();
		WebElement headlineInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
				"gai-text-form-component-profileEditFormElement-TOP-CARD-profile-ACoAADpTciYBPW5Zuy7334XlfYqiC-ZakNLXs3A-headline")));
		new Actions(driver).scrollToElement(headlineInput).perform();
		headlineInput.clear();
		//headlineInput.sendKeys(ExcelReader.getheadline());
		headlineInput.sendKeys(headline);
		

	}

	public void HeadlineUpdate() throws Exception {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li-icon[type='success-pebble-icon']")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement saveButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[starts-with(@id,'ember')]/span[text()='Save']")));
		saveButton.click();
		System.out.println("Saved Successfully");
		//Thread.sleep(2000);
		screenShot1(driver,
				"C:\\Users\\SILSOWMY\\eclipse-workspace\\LinkedinWebpage\\LinkedInProject\\ScreenShots\\Headline.jpg");
	}

	public static void screenShot1(WebDriver driver, String filePath) {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(f, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	 driver.quit();
	}
	// Scenario 2

	public void Skills() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement skillsHeadline = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id=\"profile-content\"]/div/div[2]/div/div/main/section[8]/div[2]/div/div[1]/div/h2/span[2]")));
		new Actions(driver).scrollToElement(skillsHeadline).perform();
		WebElement showAllSkillsButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[contains(@id, 'navigation-index-Show-all-')]/span")));
		showAllSkillsButton.click();
	}

	public void AllSkillsPresent() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement skillElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='JavaScript']")));
		assertEquals("JavaScript", skillElement.getText());
		System.out.println(skillElement.getText());
		
		screenShot2(driver,
				"C:\\Users\\SILSOWMY\\eclipse-workspace\\LinkedinWebpage\\LinkedInProject\\ScreenShots\\Skills.jpg");
	}

	public static void screenShot2(WebDriver driver, String filePath) {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(f, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	driver.quit();
	}

	// Scenario 3

	public void saveEntryWithoutName() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement educationHeadline = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id=\"profile-content\"]/div/div[2]/div/div/main/section[6]/div[2]/div/div[1]/div/h2/span[1]")));
		new Actions(driver).scrollToElement(educationHeadline).perform();
		assert educationHeadline.isDisplayed() : "Education headline is not displayed";

		// Click the add education button
		System.out.println("Waiting for add education button");
		WebElement addEducationButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("navigation-add-edit-deeplink-add-education")));
		addEducationButton.click();
		assert addEducationButton.isDisplayed() : "Add education button is not displayed";
	}

	public void savebutton() {
		// Click the save button without entering the name
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Waiting for save button");
		WebElement saveButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[text()='Save']]")));
		saveButton.click();
		assert saveButton.isDisplayed() : "Save button is not displayed";

		// Take a screenshot of the invalid entry
		System.out.println("Taking screenshot of invalid entry");
		screenShot3(driver,
				"C:\\Users\\SILSOWMY\\eclipse-workspace\\LinkedinWebpage\\LinkedInProject\\ScreenShots\\Invalid entry.jpg");
	}

	public static void screenShot3(WebDriver driver, String filePath) {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(f, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verifyErrorMessage() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement errorMessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.artdeco-inline-feedback__message")));
		String actualMessage = errorMessage.getText();

		// Get the expected message from Excel

		String expectedMessage = ExcelReader.getfieldName();

		// Verify the error message
		Assert.assertEquals(expectedMessage, actualMessage);
		System.out.println("Error message: " + actualMessage);
		driver.quit();
	}
	// Scenario 4

	public void ResourceButton() {
		// Wait for the "Resources" button to be visible and clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement resourcesButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//button[contains(@class,'artdeco-dropdown__trigger--placement-bottom')]/span)[4]")));

		// Click on the "Resources" button
		resourcesButton.click();
		// Wait for the dropdown to become visible

		WebElement dropdown = driver.findElement(By.xpath("//div[@class='artdeco-dropdown__content-inner']/ul"));

		// Scroll to the "Saved Items" option
		WebElement savedItemsOption = driver.findElement(By.xpath(
				"(//div[starts-with(@id,'ember')][contains(@class,'artdeco-dropdown__item--is-dropdown')] /span[text()='Saved items'])[2]"));
		new Actions(driver).scrollToElement(savedItemsOption).perform();

		// Click on the "Saved Items" option
		savedItemsOption.click();
	}

	public void SavedItemsPage() {
		// Wait for the page URL to contain "saved"
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean isCorrectPage = wait.until(ExpectedConditions.urlContains("saved"));
//	    // Assert the page title or URL to confirm navigation
		Assert.assertTrue("Navigation to the saved items page failed!", isCorrectPage);
		System.out.println("Successfully navigated to the Saved Items page");
		
		screenShot4(driver,
				"C:\\Users\\SILSOWMY\\eclipse-workspace\\LinkedinWebpage\\LinkedInProject\\ScreenShots\\Saved items.jpg");
	}
	public static void screenShot4(WebDriver driver, String filePath) {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(f, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
// Scenario 5

	public void PublicProfileURL() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement DropDown = driver.findElement(By.xpath(
//				"(//button[starts-with(@id,'ember')][contains(@class,'msg-overlay-bubble-header__control')])[2]"));
		WebElement DropDown =driver.findElement(By.xpath("//*[@id=\"ember154\"]"));
		DropDown.click();
		WebElement ProfileURL = driver.findElement(By.xpath(
				"(//div[contains(@class,'display-flex')]/a[starts-with(@id,'ember')][contains(@class,'ember-view')])[2]"));
		ProfileURL.click();
	}

	public void switchToCurrentWindow(WebDriver driver) {
		// Get the handle of the current window
		String currentWindowHandle = driver.getWindowHandle();

		// Iterate through all open window handles
		for (String handle : driver.getWindowHandles()) {
			// If the handle is not the current window, switch to it
			if (!handle.equals(currentWindowHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}

	public void ProfileSettingsNewTab() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		switchToCurrentWindow(driver);

		String actURL = driver.getCurrentUrl();

		String exURL = "https://www.linkedin.com/public-profile/settings?trk=d_flagship3_profile_self_view_public_profile";

		Assert.assertEquals(actURL, exURL);
		System.out.println("Navigated to NewTab" + actURL);
	    Thread.sleep(1000);
		screenShot5(driver,
				"C:\\Users\\SILSOWMY\\eclipse-workspace\\LinkedinWebpage\\LinkedInProject\\ScreenShots\\Profilr URL tab.jpg");
	}
	
	public static void screenShot5(WebDriver driver, String filePath) {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(f, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
}