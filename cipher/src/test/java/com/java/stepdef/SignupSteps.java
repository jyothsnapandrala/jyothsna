package com.java.stepdef;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.java.page.LoginPage;
import com.java.page.SignupPage;
import com.util.PostgresDBConnection;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignupSteps {

    WebDriver driver;
    static List<List<String>>  testData;
    static int dataIndex = 0;

    SignupPage signupPage;

    @Given("I open on the Capgemini Brand Store website")
    public void openBrandStoreWebsite() throws SQLException {
		/*
		 * Setupdriver baseSteps = new Setupdriver(); try { baseSteps.setUp(); } catch
		 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * this.driver = baseSteps.driver;
		 */
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
       driver.get("https://capgeminibrandstore.printstop.co.in/"); // replace with actual URL
       signupPage = new SignupPage(driver);
    	signupPage.goToLoginPage("https://capgeminibrandstore.printstop.co.in/");
        
        if (testData == null) {
            testData = PostgresDBConnection.getLoginTestData("SELECT email,newpassword FROM public.\\\"user db\\\"");
        }
    }
    @When("I enter Capgeimini email ID in signup form")
    public void enterEmailID() {
        if (testData == null || testData.isEmpty()) {
            throw new RuntimeException("No test data found in DB!");
        }
     
        if (dataIndex >= testData.size()) {
            throw new RuntimeException(
                "Data index " + dataIndex + " out of range. Total rows: " + testData.size()
            );
        }
     
        String[] row = testData.get(dataIndex).toArray(new String[0]);
        String email = row[0];  // take first column (email)
        signupPage.enterEmail(email);
     
        // Move to next row automatically for next scenario
        dataIndex++;
    }
    

	/*
	 * @When("I enter Capgeimini email ID in signup form") public void
	 * enterEmailID() { String email = testData.get(dataIndex)[0];
	 * signupPage.enterEmail(email); }
	 */

    @When("I click on the Login\\/Register button")
    public void clickLoginRegister() {
        signupPage.clickLoginRegister();
    }


    @Then("I should enter the Received OTP")
    public void enterOTP() {
        signupPage.enterOTP(); // Replace with actual OTP logic
    }

    @And("I set a new password and confirm the password")
    public void setNewPassword() {
    	String newpassword = testData.get(dataIndex).get(0);
        signupPage.setPassword(newpassword); // Replace with actual password logic
    }

    @Then("I have to click the Submit button")
    public void clickSubmit() {
        signupPage.clickSubmit();
    }

    }

