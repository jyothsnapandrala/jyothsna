package com.java.stepdef;
 
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.java.page.LoginPage;
import com.util.PostgresDBConnection;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    static List<List<String>> testData;
    static int dataIndex = 1;

    @Before
    public void setUp() {
        driver = com.java.driver.SetupDriver.getDriver();
        loginPage = new LoginPage(driver);
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() throws SQLException {
        loginPage.goToLoginPage("https://capgeminibrandstore.printstop.co.in/");
        if (testData == null) {
            testData = PostgresDBConnection.getLoginTestData("SELECT email,newpassword FROM public.\"user db\"");
        }
    }

    @When("I enter valid email")
    public void i_enter_valid_email() {
        String email = testData.get(dataIndex).get(0);
        loginPage.enterEmail(email);
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should enter the OTP")
    public void i_should_enter_the_otp() throws InterruptedException {
        // Pass OTP value if needed, e.g. from testData or external source
        // loginPage.enterOTP(otp);
        // If OTP is auto-read, keep as is
        // If not implemented, comment or throw exception
        // For now, assuming OTP is handled elsewhere
    	loginPage.enterOTP("");
    }

    @Then("I enter the password")
    public void i_enter_the_password() {
        String password = testData.get(dataIndex).get(1);
        loginPage.enterPassword(password);
        dataIndex++;
    }
    

    @Then("I have click the login button")
    public void i_click_the_login_button_again() {
        loginPage.clickSubmitButton();
    }

   
}