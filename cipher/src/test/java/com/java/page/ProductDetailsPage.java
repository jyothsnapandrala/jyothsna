package com.java.page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage extends BasePage {
    private WebDriverWait wait;
    private WebDriver driver;
    private List<Double> allPrices = new ArrayList<>();

    private static class ProductInfo {
        double price;
        String name;
        String link;
        ProductInfo(double price, String name, String link) {
            this.price = price;
            this.name = name;
            this.link = link;
        }
    }
    private List<ProductInfo> allProductInfos = new ArrayList<>();

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver; // Ensure driver is assigned
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Increased wait time to 60 seconds
    }
    public void pressEnter() {
    			WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"autocomplete-0-input\"]"));
        searchBar.sendKeys(Keys.ENTER);
        WebElement products=driver.findElement(By.xpath("//*[@id=\"hits-list\"]/div/div/ol/li[1]/div/a"));
        products.click();
    }

    public void collectAllPricesFromAllPages() {
        boolean productsPrinted = false;
        // Wait for the product list container first
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category_product_list")));
        } catch (Exception e) {
            System.out.println("category_product_list container not found. Current URL: " + driver.getCurrentUrl());
            System.out.println("Page source:\n" + driver.getPageSource());
            throw new IllegalStateException("category_product_list container not found.");
        }
        // Now wait for product items
        List<WebElement> products = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='category_product_list']//div[contains(@class,'product-box')]")));
            products = driver.findElements(By.xpath("//*[@id='category_product_list']//div[contains(@class,'product-box')]"));
        } catch (Exception e) {
            System.out.println("Product items not found. Current URL: " + driver.getCurrentUrl());
            System.out.println("Page source:\n" + driver.getPageSource());
            throw new IllegalStateException("Product items not found.");
        }
        if (!productsPrinted) {
            System.out.println("Products found: " + products.size());
            productsPrinted = true;
        }
        if (products == null || products.isEmpty()) {
            throw new IllegalStateException("No products found with the given XPath.");
        }
        List<String> productLinks = new ArrayList<>();
        for (WebElement product : products) {
            try {
                WebElement linkElement = product.findElement(By.xpath(".//a[contains(@class,'stretched-link')]"));
                productLinks.add(linkElement.getAttribute("href"));
            } catch (Exception e) {
                System.out.println("No product link found for a product item.");
            }
        }
        // Only collect prices for the products found on the first load
        for (String link : productLinks) {
            try {
                driver.get(link);
            } catch (Exception e) {
                System.out.println("Timeout or error loading product page: " + link);
                continue;
            }
            // Try multiple XPaths for price extraction
            List<WebElement> priceElements = null;
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class,'fs-3') and contains(text(),'₹')]")));
                priceElements = driver.findElements(By.xpath("//p[contains(@class,'fs-3') and contains(text(),'₹')]"));
            } catch (Exception e) {
                // Fallback: try another XPath
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'₹')]")));
                    priceElements = driver.findElements(By.xpath("//*[contains(text(),'₹')]"));
                } catch (Exception ex) {
                    System.out.println("No price found for product: " + link);
                    driver.navigate().back();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category_product_list")));
                    continue;
                }
            }
            String name = "";
            try {
                WebElement nameElement = driver.findElement(By.xpath("//h1"));
                name = nameElement.getText();
            } catch (Exception e) {
                name = link;
            }
            if (priceElements != null && !priceElements.isEmpty()) {
                String priceText = priceElements.get(0).getText().replace("₹", "").replace(",", "").replace("$", "").replace("per piece", "").trim();
                // Remove any non-numeric characters except dot
                priceText = priceText.replaceAll("[^0-9.]", "");
                System.out.println("Extracted price text: " + priceText + ", Product name: " + name);
                try {
                    double price = Double.parseDouble(priceText);
                    allPrices.add(price);
                    allProductInfos.add(new ProductInfo(price, name, link));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format for product: " + link + " | Raw price: " + priceText);
                }
            } else {
                System.out.println("No price found for product: " + link);
            }
            try {
                driver.navigate().back();
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category_product_list")));
                } catch (Exception e) {
                    System.out.println("Timeout or error after navigating back from product page: " + link);
                }
            } catch (org.openqa.selenium.NoSuchSessionException e) {
                System.out.println("WebDriver session is invalid or closed. Skipping navigate().back() for: " + link);
                break; // Stop further navigation if session is closed
            }
        }
        // After collecting all products, print highest and lowest price product info
        if (!allProductInfos.isEmpty()) {
            ProductInfo max = Collections.max(allProductInfos, (a, b) -> Double.compare(a.price, b.price));
            ProductInfo min = Collections.min(allProductInfos, (a, b) -> Double.compare(a.price, b.price));
            System.out.println("Highest Price Product: " + max.name + " | Price: " + max.price);
            System.out.println("Lowest Price Product: " + min.name + " | Price: " + min.price);

            // 1. Click the highest price product, add it to cart, go back twice.
            driver.get(max.link);
            try {
                WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SubmitBtn']/i")));
                addToCartBtn.click();
            } catch (Exception e) {
                System.out.println("Add to Cart button not found for highest price product.");
            }
            driver.navigate().back();
            driver.navigate().back();

            // 2. Click the lowest price product, add it to cart, then click "Continue to Checkout".
            driver.get(min.link);
            try {
                WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SubmitBtn']/i")));
                addToCartBtn.click();
            } catch (Exception e) {
                System.out.println("Add to Cart button not found for lowest price product.");
            }
            try {
                WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ps_checkout']")));
                checkoutBtn.click();
            } catch (Exception e) {
                System.out.println("Continue to Checkout button not found.");
            }
        } else {
            System.out.println("No products collected to compare prices.");
        }
    }

    public void open() {
        // Implement navigation to the product details page
    }

    public int countProducts() {
    	
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"category_product_list\"]")));
            int totalCount = 0;
            List<WebElement> products = driver.findElements(By.xpath("//*[@id=\"category_product_list\"]"));
            
			return totalCount += products.size();
            
           
        
        }
    
    // Address block actions for checkout
    public void clickAddressDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement dropdownBtn = driver.findElement(By.xpath("//*[@id=\"shipping_address\"]/div[2]/button/span"));
        System.out.println("Attempting to click dropdown button...");
        dropdownBtn.click();
        System.out.println("Dropdown button clicked.");
        // Try to find the address edit iframe, switch only if present
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        boolean switched = false;
        for (WebElement frame : frames) {
            String name = frame.getAttribute("name");
            String id = frame.getAttribute("id");
            if ((name != null && name.contains("address")) || (id != null && id.contains("address")) || (id != null && id.contains("offcanvasRight_for_shippingLabel"))) {
                driver.switchTo().frame(frame);
                switched = true;
                break;
            }
        }
        WebElement editBtn = driver.findElement(By.xpath("//*[@id=\"shipping_address\"]/div[2]/ul/li[1]/button"));
        editBtn.click();
        if (switched) driver.switchTo().defaultContent();
    }

	/*
	 * public void clickEditAddress() { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(20)); // clickAddressDropdown();
	 * System.out.
	 * println("Attempting to locate Edit Address button by class and text...");
	 * WebElement editBtn = driver.findElement(By.xpath(
	 * "//*[@id=\"shipping_address\"]/div[2]/ul/li[1]/button"));
	 * 
	 * editBtn.click(); System.out.println("Edit Address button clicked.");
	 * 
	 * }
	 */
    public void fillAddressDetails(String firstName, String lastName, String companyName, String country, String pinCode, String state, String city, String addressDetails, String landmark, String contactNumber, String gstin) throws InterruptedException {
        boolean found = false;
        int frameCount = driver.findElements(By.tagName("iframe")).size();
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Found " + frameCount + " iframes on the page.");
        int maxRetries = 3;
        StringBuilder diagnostics = new StringBuilder();
        for (int i = 0; i < frameCount; i++) {
            int retryCount = 0;
            while (retryCount < maxRetries) {
                try {
                    List<WebElement> frames = driver.findElements(By.tagName("iframe"));
                    if (frames.size() <= i) break;
                    WebElement frame = frames.get(i);
                    String name = "";
                    String id = "";
                    String src = "";
                    try { name = frame.getAttribute("name"); } catch (org.openqa.selenium.StaleElementReferenceException e) { retryCount++; Thread.sleep(500); continue; }
                    try { id = frame.getAttribute("id"); } catch (org.openqa.selenium.StaleElementReferenceException e) { retryCount++; Thread.sleep(500); continue; }
                    try { src = frame.getAttribute("src"); } catch (org.openqa.selenium.StaleElementReferenceException e) { retryCount++; Thread.sleep(500); continue; }
                    diagnostics.append("Trying iframe index ").append(i).append(" name: ").append(name).append(" id: ").append(id).append(" src: ").append(src).append("\n");
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(frame);
                    Thread.sleep(1000); // Wait for dynamic content
                    System.out.println("Switched to iframe index " + i + " (name: " + name + ", id: " + id + ", src: " + src + ")");
                    // Try multiple locator strategies for firstName field
                    WebElement firstNameField = null;
                    try { firstNameField = driver.findElement(By.id("firstName")); diagnostics.append("Tried By.id('firstName')\n"); } catch (Exception e) {}
                    if (firstNameField == null) {
                        try { firstNameField = driver.findElement(By.name("firstName")); diagnostics.append("Tried By.name('firstName')\n"); } catch (Exception e) {}
                    }
                    if (firstNameField == null) {
                        try { firstNameField = driver.findElement(By.xpath("//input[contains(@placeholder,'First Name') or contains(@aria-label,'First Name') or contains(@name,'firstName') or contains(@id,'firstName') or contains(@id,'firstname') or contains(@name,'firstname')]") ); diagnostics.append("Tried By.xpath for placeholder/aria-label/name/id\n"); } catch (Exception e) {}
                    }
                    if (firstNameField != null) {
                        found = true;
                        if (firstName != null) {
                            firstNameField.clear();
                            firstNameField.sendKeys(firstName);
                        }
                        break;
                    } else {
                        diagnostics.append("firstName field not found in iframe index ").append(i).append("\n");
                        diagnostics.append("Iframe HTML:\n").append(driver.getPageSource()).append("\n");
                    }
                    break;
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    retryCount++;
                    Thread.sleep(500);
                }
            }
            if (found) break;
        }
        if (!found) {
            System.out.println("Diagnostics for firstName field not found:\n" + diagnostics.toString());
            throw new RuntimeException("Could not find address form fields in any iframe after retries. See diagnostics above.");
        }
        // Now fill the form (try all strategies for firstName field)
        WebElement firstNameField = null;
        try { firstNameField = driver.findElement(By.id("firstName")); } catch (Exception e) {}
        if (firstNameField == null) {
            try { firstNameField = driver.findElement(By.name("firstName")); } catch (Exception e) {}
        }
        if (firstNameField == null) {
            try { firstNameField = driver.findElement(By.xpath("//input[contains(@placeholder,'First Name') or contains(@aria-label,'First Name')]")); } catch (Exception e) {}
        }
        if (firstNameField != null && firstName != null) {
            firstNameField.clear();
            firstNameField.sendKeys(firstName);
        } else {
            System.out.println("ERROR: firstName field is still not found after all strategies.");
        }
        if (lastName != null) {
            try {
                WebElement lastNameField = driver.findElement(By.xpath("//*[@id=\"lastname\"]"));
                if (lastNameField.isEnabled() && lastNameField.isDisplayed()) {
                    lastNameField.clear();
                    lastNameField.sendKeys(lastName);
                } else {
                    System.out.println("lastName field is not enabled or not displayed.");
                }
            } catch (Exception e) {
                System.out.println("lastName field not found or not interactable.");
            }
        }
        if (companyName != null) {
            try {
                WebElement companyNameField = driver.findElement(By.xpath("//*[@id=\"companyname\"]"));
                if (companyNameField.isEnabled() && companyNameField.isDisplayed()) {
                    companyNameField.clear();
                    companyNameField.sendKeys(companyName);
                } else {
                    System.out.println("companyName field is not enabled or not displayed.");
                }
            } catch (Exception e) {
                System.out.println("companyName field not found or not interactable.");
            }
        }
        if (country != null) {
            try {
                WebElement countryDropdown = driver.findElement(By.xpath("//*[@id='Frmship']/div/div[5]/div/div/div/button"));
                countryDropdown.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bs-select-1-1']")));
                WebElement countryOption = driver.findElement(By.xpath("//*[@id='bs-select-1-1']"));
                countryOption.click();
            } catch (Exception e) {
                System.out.println("Country dropdown or option not found.");
            }
        }
        if (pinCode != null) {
            try {
                WebElement pinCodeField = driver.findElement(By.xpath("//*[@id=\"postcode\"]"));
                if (pinCodeField.isEnabled() && pinCodeField.isDisplayed()) {
                    pinCodeField.clear();
                    pinCodeField.sendKeys(pinCode);
                } else {
                    System.out.println("pinCode field is not enabled or not displayed.");
                }
            } catch (Exception e) {
                System.out.println("pinCode field not found or not interactable.");
            }
        }
        if (city != null) {
        	Thread.sleep(3000); // Wait for any dynamic loading
            try {
                WebElement cityField = driver.findElement(By.xpath("//*[@id='city']"));
                String tagName = cityField.getTagName();
                if ("input".equalsIgnoreCase(tagName)) {
                    cityField.clear();
                    cityField.sendKeys(city);
                    Thread.sleep(500); // Allow any internal validation to complete
                    // Optionally check for validation errors
                    List<WebElement> errorMessages = driver.findElements(By.xpath("//*[contains(@class,'error') or contains(@class,'invalid') or contains(@class,'alert')]"));
                    for (WebElement error : errorMessages) {
                        System.out.println("City field validation message: " + error.getText());
                    }
                } else {
                    System.out.println("City field is not an input. Tag: " + tagName);
                }
            } catch (Exception e) {
                System.out.println("City field not found or not interactable.");
            }
        }
        if (state != null) {
            try {
                WebElement stateField = driver.findElement(By.xpath("//*[@id=\"state\"]"));
                String tagName = stateField.getTagName();
                if (stateField.isEnabled() && stateField.isDisplayed() && ("input".equalsIgnoreCase(tagName) || "textarea".equalsIgnoreCase(tagName))) {
                    stateField.clear();
                    stateField.sendKeys(state);
                } else if ("select".equalsIgnoreCase(tagName)) {
                    org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(stateField);
                    select.selectByVisibleText(state);
                } else {
                    System.out.println("state field is not an editable input or select. Tag: " + tagName);
                }
            } catch (Exception e) {
                System.out.println("state field not found or not interactable.");
            }
        }
        // City field handling: send keys only, no suggestion list
       
        if (addressDetails != null) {
            try {
                WebElement addressDetailsField = driver.findElement(By.xpath("//*[@id=\"street_address\"]"));
                if (addressDetailsField.isEnabled() && addressDetailsField.isDisplayed()) {
                    addressDetailsField.clear();
                    addressDetailsField.sendKeys(addressDetails);
                } else {
                    System.out.println("addressDetails field is not enabled or not displayed.");
                }
            } catch (Exception e) {
                System.out.println("addressDetails field not found or not interactable.");
            }
        }
        if (landmark != null) {
            try {
                WebElement landmarkField = driver.findElement(By.xpath("//*[@id=\"suburb\"]"));
                if (landmarkField.isEnabled() && landmarkField.isDisplayed()) {
                    landmarkField.clear();
                    landmarkField.sendKeys(landmark);
                } else {
                    System.out.println("landmark field is not enabled or not displayed.");
                }
            } catch (Exception e) {
                System.out.println("landmark field not found or not interactable.");
            }
        }
        if (contactNumber != null) {
            try {
                WebElement contactNumberField = driver.findElement(By.xpath("//*[@id=\"phone_number\"]"));
                if (contactNumberField.isEnabled() && contactNumberField.isDisplayed()) {
                    contactNumberField.clear();
                    contactNumberField.sendKeys(contactNumber);
                } else {
                    System.out.println("contactNumber field is not enabled or not displayed.");
                }
            } catch (Exception e) {
                System.out.println("contactNumber field not found or not interactable.");
            }
        }
        if (gstin != null) {
            try {
                WebElement gstinField = driver.findElement(By.xpath("//*[@id='extra_fields[2][1]']"));
                if (gstinField.isEnabled() && gstinField.isDisplayed()) {
                    gstinField.clear();
                    gstinField.sendKeys(gstin);
                } else {
                    System.out.println("gstin field is not enabled or not displayed.");
                }
            } catch (Exception e) {
                System.out.println("gstin field not found or not interactable.");
            }
        }
        // Click Save Changes after filling address details
        WebElement clicksavechanges = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Submit']")));
        wait.until(ExpectedConditions.visibilityOf(clicksavechanges));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clicksavechanges);
        try {
            clicksavechanges.click();
        } catch (Exception ex1) {
            System.out.println("Standard click failed for Save Changes. Trying JS click.");
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", clicksavechanges);
        }
        // Wait for page update after Save Changes
        Thread.sleep(3000); // Increase wait time for page update
        // Try to click Continue button
        boolean continueFound = false;
        StringBuilder continueDiagnostics = new StringBuilder();
        int continueRetries = 3;
        for (int retry = 0; retry < continueRetries && !continueFound; retry++) {
            Thread.sleep(2000);
            // Try main page first
            try {
                WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='order_continue']")));
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueBtn);
                try {
                    continueBtn.click();
                } catch (Exception ex3) {
                    System.out.println("Standard click failed for Continue. Trying JS click.");
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
                }
                continueFound = true;
                break;
            } catch (Exception e) {
                continueDiagnostics.append("Main page: 'order_continue' button not found in retry ").append(retry).append("\n");
            }
            // Try all iframes if not found on main page
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            for (int i = 0; i < frames.size(); i++) {
                try {
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(frames.get(i));
                    WebElement continueBtnIframe = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='order_continue']")));
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueBtnIframe);
                    try {
                        continueBtnIframe.click();
                    } catch (Exception ex4) {
                        System.out.println("Standard click failed for Continue in iframe. Trying JS click.");
                        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtnIframe);
                    }
                    continueFound = true;
                    break;
                } catch (Exception e) {
                    continueDiagnostics.append("Iframe index ").append(i).append(": 'order_continue' button not found in retry ").append(retry).append("\n");
                }
            }
            driver.switchTo().defaultContent();
        }
        if (!continueFound) {
            continueDiagnostics.append("All strategies failed. Printing page source and error messages.\n");
            continueDiagnostics.append("Current page source:\n").append(driver.getPageSource()).append("\n");
            System.out.println("Diagnostics for 'order_continue' button not found:\n" + continueDiagnostics.toString());
            throw new RuntimeException("'order_continue' button not found or not clickable after saving address. See diagnostics above.");
        }
    }
    // Method to fill address details with an invalid pincode
   

    // Method to get the error message for invalid pincode
    public String getPincodeErrorMessage() {
        try {
            // 1. Try to find error message with common error classes
            List<WebElement> errorMessages = driver.findElements(By.xpath("//*[contains(@class,'error') or contains(@class,'invalid') or contains(@class,'alert')]"));
            for (WebElement error : errorMessages) {
                String text = error.getText();
                if (text != null && !text.trim().isEmpty()) {
                    return text.trim();
                }
            }
            // 2. Fallback: try the original specific XPath
            try {
                WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Frmship\"]/div/div[6]/div/div/span[1]/span")));
                String text = errorMsg.getText();
                if (text != null && !text.trim().isEmpty()) {
                    return text.trim();
                }
            } catch (Exception e) {
                // Ignore
            }
            // 3. Print diagnostics if nothing found
            System.out.println("No pincode error message found. Page source:\n" + driver.getPageSource());
            return null;
        } catch (Exception e) {
            System.out.println("Exception in getPincodeErrorMessage: " + e.getMessage());
            return null;
        }
    
    }
}