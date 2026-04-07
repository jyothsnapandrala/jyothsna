package com.java.stepdef;

import io.cucumber.java.en.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.java.page.LoginPage;
import com.util.PostgresDBConnection;

public class NegativeLoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    static List<List<String>>  testData;
    static int dataIndex = 0;
   
    	
    
    @Given("I am in the login page")
    public void i_am_in_the_login_page() throws SQLException {
        
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
       driver.get("https://capgeminibrandstore.printstop.co.in/"); // replace with actual URL
        loginPage = new LoginPage(driver);
        
        if (testData == null) {
            testData = PostgresDBConnection.getLoginTestData("SELECT email,newpassword FROM public.\"user db\"");}// Replace with actual login URL
    }

    @When("I leave the email field blank")
    public void i_leave_the_email_field_blank() {
        loginPage.enterEmail(""); // Leave email field empty
        // Optional
    }

    @And("I click login button")
    public void i_click_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should see a validation message {string}")
    public void i_should_see_a_validation_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        System.out.println("Expected: " + expectedMessage);
        System.out.println("Actual: " + actualMessage);
        assertNotNull(actualMessage, "Validation message should not be null");
        assertEquals(actualMessage.trim(), expectedMessage.trim());
    }
}
