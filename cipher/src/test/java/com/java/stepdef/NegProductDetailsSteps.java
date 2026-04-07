package com.java.stepdef;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.java.driver.SetupDriver;
import com.java.page.ProductDetailsPage;
import com.util.PostgresDBConnection;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NegProductDetailsSteps {
	 private WebDriver driver;
	 private ProductDetailsPage productDetailsPage;
	private List<List<String>> testData;
    //private List<Double> collectedPrices; // Added for collecting prices
	
  
    @Before
    public void setUp() {
        driver = SetupDriver.getDriver(); // or your driver setup logic
        productDetailsPage = new ProductDetailsPage(driver);
    }   

    @Given("user is on the advanced search page with search term")
    public void user_is_on_the_advanced_search_page_with_search_term() {
        // Implement navigation if needed
        // negProductDetailsPage.open();
    	productDetailsPage.pressEnter();
    }

    @Then("I have fills address details from database and saves changes")
    public void i_have_fills_address_details_from_database_and_saves_changes() throws SQLException, InterruptedException {
        testData = PostgresDBConnection.getLoginTestData("SELECT \"First Name\", \"Last Name\", \"Company Name\", \"Country\", \"Pin Code / Zip Code\", \"State\", \"City\", \"Address Details\", \"Landmark\", \"Contact Number\", \"GSTIN\" FROM public.\"Address\"");
        System.out.println("Fetched testData: " + testData);
        // Get the first row (index 0) directly
        List<String> row = testData.get(1);
        System.out.println("Using address row for test: " + row);
        productDetailsPage.fillAddressDetails(
            row.get(0),
            row.get(1),
            row.get(2),
            row.get(3),     
            row.get(4),
            row.get(5),
            row.get(6),
            row.get(7),
            row.get(8),
            row.get(9),
            row.get(10)
        );
    }

  
	/*
	 * @Then("I have fills address details from database and saves changes") public
	 * void i_have_fills_address_details_from_database_and_saves_changes() {
	 * negProductDetailsPage.clickSaveChanges(); }
	 */

	
	

    @When("I on the user navigates through all result pages")
    public void im_on_the_user_navigates_through_all_result_pages() {
        // This step is covered by collecting prices across all pages
    	int count = productDetailsPage.countProducts();
        System.out.println("Total products displayed: " + count);
    }

    @When("I  collects all product prices and compare highest price and lowest price")
    public void im_collects_all_product_prices_and_compare_highest_price_and_lowest_price() {
        // Ensure navigation to product listing page before collecting prices
        //negProductDetailsPage.navigateToProductListingPage();
        productDetailsPage.collectAllPricesFromAllPages();
        }
      
    

    @When("I have clicks the address dropdown")
    public void i_have_clicks_the_address_dropdown() {
        productDetailsPage.clickAddressDropdown();
        
    }
   
    

    @Then("I should see an error message for the incorrect pincode")
    public void i_should_see_an_error_message_for_the_incorrect_pincode() {
        String errorMsg = productDetailsPage.getPincodeErrorMessage();
        assertNotNull(errorMsg, "Expected an error message for invalid pincode, but none was found.");
        System.out.println("Pincode error message: " + errorMsg);
    }
    }
