// SearchPage.java
package com.java.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class SearchPage extends BasePage {
    private WebDriverWait wait;

    private By searchBox = By.xpath("//*[@id=\"autocomplete-0-input\"]");
    private By suggestionList = By.xpath("//*[@id=\"autocomplete-0-products-list\"]");
    private By scrollbar = By.xpath("/html/body/div[8]/div/div[1]");

    public SearchPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

   

    

    public void waitForSearchBox() {
        // Try main document first
    	// Example fix for waitForSearchBox and searchForItem

    	
    	    WebElement searchBoxElement = driver.findElement(searchBox);
    	    
    	
    	    
    	   
    	

    	    searchBoxElement.click();
      
        }
    

    public void searchForItem(String item) {
        WebElement searchBoxElement = driver.findElement(searchBox);
        searchBoxElement.sendKeys(item);
        
    }

    public void scrollThroughSuggestions() {
        List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestionList));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (WebElement suggestion : suggestions) {
            js.executeScript("arguments[0].scrollIntoView(true);", suggestion);
        }
    }

    public boolean validateItemsInSuggestions(List<String> expectedItems) {
        List<WebElement> suggestions = driver.findElements(suggestionList);
        System.out.println("--- Suggestions found ---");
        for (WebElement suggestion : suggestions) {
            System.out.println(suggestion.getText());
        }
        System.out.println("------------------------");
        for (String item : expectedItems) {
            boolean found = suggestions.stream().anyMatch(s -> s.getText().toLowerCase().contains(item.toLowerCase()));
            if (!found) {
                System.out.println("Item not found in suggestions: " + item);
                return false;
            }
        }
        return true;
    }
}
