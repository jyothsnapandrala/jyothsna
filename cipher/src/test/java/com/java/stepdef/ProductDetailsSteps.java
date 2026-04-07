// ProductDetailsSteps.java
package com.java.stepdef;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.java.driver.SetupDriver;
import com.java.page.ProductDetailsPage;
import com.util.PostgresDBConnection;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductDetailsSteps {
    
    
 
        private WebDriver driver;
        private ProductDetailsPage productDetailsPage;
        private List<List<String>> testData;

        @Before
        public void setUp() {
            driver = SetupDriver.getDriver(); // or your driver setup logic
            productDetailsPage = new ProductDetailsPage(driver);
        }   

    @Given("the user is on the advanced search page with search term")
    public void the_user_is_on_the_advanced_search_page_with_search_term() {
  	 productDetailsPage.pressEnter();
           
    }

    @When("the user navigates through all result pages")
    public void the_user_navigates_through_all_result_pages() {
    	int count = productDetailsPage.countProducts();
        System.out.println("Total products displayed: " + count);}
        //productDetailsPage.countProducts();}

    
    @When("the user collects all product prices and compare highest price and lowest price")
    public void the_user_collects_all_product_prices_and_compare_highest_price_and_lowest_price() {
        productDetailsPage.collectAllPricesFromAllPages();
    }
    @When("user clicks the address dropdown")
    public void user_clicks_address_dropdown() {
        productDetailsPage.clickAddressDropdown();
    }

	/*
	 * @When("user clicks the edit address button") public void
	 * user_clicks_edit_address_button() { // productDetailsPage.clickEditAddress();
	 * }
	 */

    @Then("user fills address details from database and saves changes")
    public void user_fills_address_details_from_database_and_saves_changes() throws SQLException, InterruptedException {
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
	 * @Then("user clicks continue to complete the order") public void
	 * user_clicks_continue_to_complete_order() {
	 * productDetailsPage.clickContinue(); }
	 */

}