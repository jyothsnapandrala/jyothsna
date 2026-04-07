package com.java.stepdef;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.java.driver.SetupDriver;
import com.java.page.SearchPage;
import com.util.PostgresDBConnection;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {

   
    SearchPage searchPage;
    List<List<String>> testData;
    List<String> searchItems;

    

   

    @When("the user search for an item fetched from the database")
    public void user_searches_for_item() throws SQLException {
        testData = PostgresDBConnection.getLoginTestData("SELECT item FROM public.\"Items\"");
        searchItems = testData.stream().map(row -> row.get(0)).collect(Collectors.toList());
        searchPage = new SearchPage(SetupDriver.getDriver());
        searchPage.waitForSearchBox();
        // Search for the first item only (you can loop if needed)
        searchPage.searchForItem(searchItems.get(1));
    }

    @Then("the system scroll down in the search dropdown")
    public void system_scrolls_dropdown() {
        searchPage.scrollThroughSuggestions();
    }

	/*
	 * @Then("the user should be able to validate the items") public void
	 * user_validates_items() { boolean allFound =
	 * searchPage.validateItemsInSuggestions(searchItems);
	 * Assert.assertTrue(allFound,
	 * "Not all expected items were found in suggestions"); }
	 */
    @And("the user should be able to validate the  items")
    public void the_user_should_be_able_to_validate_the_items() {
        // Example: Validate some expected items in the search suggestions
        List<String> expectedItems = Arrays.asList("bag"); // Update as needed
        SearchPage searchPage = new SearchPage(SetupDriver.getDriver());
        boolean result = searchPage.validateItemsInSuggestions(expectedItems);
        if (!result) {
            throw new AssertionError("Some expected items were not found in the suggestions.");
        }
    }

   
}