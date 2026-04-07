package com.stepdefinition;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;

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
import com.pages.LoginPage;
import com.pages.ProfilePage;
import com.parameters.ExcelReader;
import com.parameters.PropertiesReader;
import com.setup.BaseSteps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Profile {
	WebDriver driver;
	PropertiesReader propertiesReader;
	LoginPage loginPage;
	ProfilePage profilepage;
	ExcelReader excelReader = new ExcelReader();
	
	

	@Given("I go to the LinkedIn login page")
	public void i_go_to_the_linkedin_login_page() throws Exception {
		BaseSteps baseSteps = new BaseSteps();
		baseSteps.setUp();
		this.driver = baseSteps.driver;
	}

	@When("I enter username and password and click on sign in button")
	public void i_enter_username_and_password_and_click_on_sign_in_button() {
		propertiesReader = new PropertiesReader();
		loginPage = new LoginPage(driver);
		loginPage.login(propertiesReader.getProperty("username"), propertiesReader.getProperty("password"));
	}

	@When("I go to the view profile page")
	public void i_go_to_the_view_profile_page() {
		profilepage = new ProfilePage(driver);
		profilepage.ViewProfile();

	}

//--------------------------1st scenario---------------------------------------
	// ---------------------------------------------------------------
	/*
	 * Created By : Silveru Sowmya Reviewed By : Motive : Ensure the user's profile
	 * headline is updated correctly as specified.
	 */
	@When("I click on Editicon and enter {string}")
	public void i_click_on_editicon_and_enter(String headline) {
		
		try {
			profilepage.Headline(headline);
			//profilepage.Headline(propertiesReader.getProperty(headline));
			// driver.navigate().back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("I click on save button and headline is saved successfully")
	public void i_click_on_save_button_and_headline_is_saved_successfully() {
		try {
			profilepage.HeadlineUpdate();
			// driver.navigate().back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//--------------------------------2nd scenario-----------------------------------------------------
	// ---------------------------------------------------------------
	/*
	 * Created By : Silveru Sowmya
	 *  Reviewed By :
	 *   Motive : Verify that all skills are visible on the user's profile page.
	 */
	@When("I go to Skills field")
	public void  i_go_to_skills_field() {
		profilepage.Skills();
	}

	@Then("I click on All Skills Present and display one Skill")
	public void i_click_on_all_skills_Present_and_display_one_Skill() {
		profilepage.AllSkillsPresent();
	}

//--------------------------------------------3rd scenario------------------------------------------
	// ---------------------------------------------------------------
	/*
	 * Created By : Silveru Sowmya 
	 * Reviewed By : 
	 * Motive : Ensure that an error message is displayed when attempting to save an education entry without a
	 * name.
	 */
	@When("I click on editicon of School")
	public void i_click_on_editicon_of_school() {
		try {
			profilepage.saveEntryWithoutName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("I click on save button")
	public void i_click_on_save_button() {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		profilepage.savebutton();
	}

	@Then("I should see an \"error\"")
	public void i_should_see_an_error() {

		try {
			profilepage.verifyErrorMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------------------------------4th scenario-----------------------------------------------------
	// ---------------------------------------------------------------
	/*
	 * Created By : Silveru Sowmya
	 *  Reviewed By : 
	 *  Motive : Ensure the user can successfully navigate to the Saved Items page from the Resources menu
	 */
	@When("I click on Resources and then SavedItems button")
	public void i_click_on_the_resources_and_then_saveditems_button() {
		profilepage.ResourceButton();
	}

	@Then("It should navigate to SavedItems page")
	public void it_should_navigate_to_saveditems_page() {
//		
		profilepage.SavedItemsPage();
	}

//-------------------------------5th scenario------------------------------------------------------
	// ---------------------------------------------------------------
	/*
	 * Created By : Silveru Sowmya 
	 * Reviewed By : 
	 * Motive : Verify that the user can access their public profile settings in a new tab or window and confirm the
	 * correct URL
	 */

	@When("I click on editicon of Public Profile and URL")
	public void i_click_on_editicon_of_public_profile_and_url() {

		profilepage.PublicProfileURL();
	}

	@Then("I navigated to new tab with change of URL")
	public void i_navigated_to_new_tab_with_change_of_url() {
		try {
			profilepage.ProfileSettingsNewTab();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}