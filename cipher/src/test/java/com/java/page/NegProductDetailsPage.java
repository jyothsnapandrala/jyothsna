/*
 * package com.java.page;
 * 
 * import java.time.Duration;
 * 
 * import java.util.ArrayList; import java.util.Collections; import
 * java.util.List;
 * 
 * import org.openqa.selenium.By; import org.openqa.selenium.Keys; import
 * org.openqa.selenium.WebDriver; import org.openqa.selenium.WebElement; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import
 * org.openqa.selenium.support.ui.WebDriverWait;
 * 
 * 
 * 
 * 
 * 
 * public class NegProductDetailsPage extends BasePage { private WebDriverWait
 * wait; private WebDriver driver; private List<Double> allPrices = new
 * ArrayList<>(); private static class ProductInfo { double price; String name;
 * String link; ProductInfo(double price, String name, String link) { this.price
 * = price; this.name = name; this.link = link; } } private List<ProductInfo>
 * allProductInfos = new ArrayList<>(); public NegProductDetailsPage(WebDriver
 * driver) { super(driver); this.driver = driver; this.wait = new
 * WebDriverWait(driver, java.time.Duration.ofSeconds(30)); }
 * 
 * public String fillAddressFormMissingFirstName(String firstName, String
 * lastName, String companyName, String country, String pinCode, String state,
 * String city, String addressDetails, String landmark, String contactNumber,
 * String gstin) throws InterruptedException { // Explicitly clear the First
 * Name field to ensure it is empty or set as provided try { WebElement
 * firstNameField = driver.findElement(By.xpath("//*[@id='firstname']"));
 * firstNameField.clear(); if (firstName != null && !firstName.isEmpty()) {
 * firstNameField.sendKeys(firstName); } } catch (Exception e) {
 * System.out.println("First Name field not found or not interactable."); } //
 * Leave first name empty try { WebElement lastNameField =
 * driver.findElement(By.xpath("//*[@id='lastname']")); lastNameField.clear();
 * lastNameField.sendKeys(lastName); } catch (Exception e) {} try { WebElement
 * companyNameField = driver.findElement(By.xpath("//*[@id='companyname']"));
 * companyNameField.clear(); companyNameField.sendKeys(companyName); } catch
 * (Exception e) {} try { WebElement countryDropdown =
 * driver.findElement(By.xpath(
 * "//*[@id='Frmship']/div/div[5]/div/div/div/button"));
 * countryDropdown.click();
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
 * "//*[@id='bs-select-1-1']"))); WebElement countryOption =
 * driver.findElement(By.xpath("//*[@id='bs-select-1-1']"));
 * countryOption.click(); } catch (Exception e) {} try { WebElement pinCodeField
 * = driver.findElement(By.xpath("//*[@id='postcode']")); pinCodeField.clear();
 * pinCodeField.sendKeys(pinCode); } catch (Exception e) {} try { WebElement
 * stateField = driver.findElement(By.xpath("//*[@id='state']"));
 * stateField.clear(); stateField.sendKeys(state); } catch (Exception e) {} try
 * { WebElement cityField = driver.findElement(By.xpath("//*[@id='city']"));
 * cityField.clear(); cityField.sendKeys(city); } catch (Exception e) {} try {
 * WebElement addressDetailsField =
 * driver.findElement(By.xpath("//*[@id='street_address']"));
 * addressDetailsField.clear(); addressDetailsField.sendKeys(addressDetails); }
 * catch (Exception e) {} try { WebElement landmarkField =
 * driver.findElement(By.xpath("//*[@id='suburb']")); landmarkField.clear();
 * landmarkField.sendKeys(landmark); } catch (Exception e) {} try { WebElement
 * contactNumberField = driver.findElement(By.xpath("//*[@id='phone_number']"));
 * contactNumberField.clear(); contactNumberField.sendKeys(contactNumber); }
 * catch (Exception e) {} try { WebElement gstinField =
 * driver.findElement(By.xpath("//*[@id='extra_fields[2][1]']"));
 * gstinField.clear(); gstinField.sendKeys(gstin); } catch (Exception e)
 * {System.out.println("gstin field not found or not interactable.");}
 * WebElement clicksavechanges =
 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
 * "//*[@id='Submit']")));
 * wait.until(ExpectedConditions.visibilityOf(clicksavechanges));
 * ((org.openqa.selenium.JavascriptExecutor)
 * driver).executeScript("arguments[0].scrollIntoView(true);",
 * clicksavechanges); try { clicksavechanges.click(); } catch (Exception ex1) {
 * System.out.println("Standard click failed for Save Changes. Trying JS click."
 * ); ((org.openqa.selenium.JavascriptExecutor)
 * driver).executeScript("arguments[0].click();", clicksavechanges); } // Return
 * the error message if present return getFirstNameValidationError(); }
 * 
 * public String getFirstNameValidationError() { try { // More robust: find any
 * <span> with the exact error text WebElement errorElement = wait.until(
 * ExpectedConditions.visibilityOfElementLocated( By.xpath("getText().trim()") )
 * ); return errorElement.getText(); } catch (Exception e) {
 * System.out.println("First name validation error not found. Page source:\n" +
 * driver.getPageSource()); return null; // No error message found } }
 * 
 * 
 * 
 * 
 * // Simulates pressing the Enter key (can be customized for your page) public
 * void pressEnter() { WebElement searchBar =
 * driver.findElement(By.xpath("//*[@id=\"autocomplete-0-input\"]"));
 * searchBar.sendKeys(Keys.ENTER); WebElement
 * products=driver.findElement(By.xpath(
 * "//*[@id=\"hits-list\"]/div/div/ol/li[1]/div/a")); products.click(); }
 * 
 * 
 * 
 * public void collectAllProductPrices() { boolean productsPrinted = false; try
 * { wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
 * "category_product_list"))); } catch (Exception e) {
 * System.out.println("category_product_list container not found. Current URL: "
 * + driver.getCurrentUrl()); System.out.println("Page source:\n" +
 * driver.getPageSource()); throw new
 * IllegalStateException("category_product_list container not found."); } // Now
 * wait for product items List<WebElement> products = null; try {
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
 * "//*[@id='category_product_list']//div[contains(@class,'product-box')]")));
 * products = driver.findElements(By.xpath(
 * "//*[@id='category_product_list']//div[contains(@class,'product-box')]")); }
 * catch (Exception e) {
 * System.out.println("Product items not found. Current URL: " +
 * driver.getCurrentUrl()); System.out.println("Page source:\n" +
 * driver.getPageSource()); throw new
 * IllegalStateException("Product items not found."); } if (!productsPrinted) {
 * System.out.println("Products found: " + products.size()); productsPrinted =
 * true; } if (products == null || products.isEmpty()) { throw new
 * IllegalStateException("No products found with the given XPath."); }
 * List<String> productLinks = new ArrayList<>(); for (WebElement product :
 * products) { try { WebElement linkElement =
 * product.findElement(By.xpath(".//a[contains(@class,'stretched-link')]"));
 * productLinks.add(linkElement.getAttribute("href")); } catch (Exception e) {
 * System.out.println("No product link found for a product item."); } } // Only
 * collect prices for the products found on the first load for (String link :
 * productLinks) { try { driver.get(link); } catch (Exception e) {
 * System.out.println("Timeout or error loading product page: " + link);
 * continue; } // Try multiple XPaths for price extraction List<WebElement>
 * priceElements = null; try {
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.
 * xpath("//p[contains(@class,'fs-3') and contains(text(),'₹')]")));
 * priceElements = driver.findElements(By.
 * xpath("//p[contains(@class,'fs-3') and contains(text(),'₹')]")); } catch
 * (Exception e) { // Fallback: try another XPath try {
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
 * "//*[contains(text(),'₹')]"))); priceElements =
 * driver.findElements(By.xpath("//*[contains(text(),'₹')]")); } catch
 * (Exception ex) { System.out.println("No price found for product: " + link);
 * driver.navigate().back();
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
 * "category_product_list"))); continue; } } String name = ""; try { WebElement
 * nameElement = driver.findElement(By.xpath("//h1")); name =
 * nameElement.getText(); } catch (Exception e) { name = link; } if
 * (priceElements != null && !priceElements.isEmpty()) { String priceText =
 * priceElements.get(0).getText().replace("₹", "").replace(",", "").replace("$",
 * "").replace("per piece", "").trim(); // Remove any non-numeric characters
 * except dot priceText = priceText.replaceAll("[^0-9.]", "");
 * System.out.println("Extracted price text: " + priceText + ", Product name: "
 * + name); try { double price = Double.parseDouble(priceText);
 * allPrices.add(price); allProductInfos.add(new ProductInfo(price, name,
 * link)); } catch (NumberFormatException e) {
 * System.out.println("Invalid price format for product: " + link +
 * " | Raw price: " + priceText); } } else {
 * System.out.println("No price found for product: " + link); } try {
 * driver.navigate().back(); try {
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
 * "category_product_list"))); } catch (Exception e) { System.out.
 * println("Timeout or error after navigating back from product page: " + link);
 * } } catch (org.openqa.selenium.NoSuchSessionException e) { System.out.
 * println("WebDriver session is invalid or closed. Skipping navigate().back() for: "
 * + link); break; // Stop further navigation if session is closed } } // After
 * collecting all products, print highest and lowest price product info if
 * (!allProductInfos.isEmpty()) { ProductInfo max =
 * Collections.max(allProductInfos, (a, b) -> Double.compare(a.price, b.price));
 * ProductInfo min = Collections.min(allProductInfos, (a, b) ->
 * Double.compare(a.price, b.price));
 * System.out.println("Highest Price Product: " + max.name + " | Price: " +
 * max.price); System.out.println("Lowest Price Product: " + min.name +
 * " | Price: " + min.price);
 * 
 * // 1. Click the highest price product, add it to cart, go back twice.
 * driver.get(max.link); try { WebElement addToCartBtn =
 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
 * "//*[@id='SubmitBtn']/i"))); addToCartBtn.click(); } catch (Exception e) {
 * System.out.println("Add to Cart button not found for highest price product."
 * ); } driver.navigate().back(); driver.navigate().back();
 * 
 * // 2. Click the lowest price product, add it to cart, then click
 * "Continue to Checkout". driver.get(min.link); try { WebElement addToCartBtn =
 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
 * "//*[@id='SubmitBtn']/i"))); addToCartBtn.click(); } catch (Exception e) {
 * System.out.println("Add to Cart button not found for lowest price product.");
 * } try { WebElement checkoutBtn =
 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
 * "//*[@id='ps_checkout']"))); checkoutBtn.click(); } catch (Exception e) {
 * System.out.println("Continue to Checkout button not found."); } } else {
 * System.out.println("No products collected to compare prices."); } // Only
 * attempt to click 'Submit' if it is present and displayed List<WebElement>
 * submitButtons = driver.findElements(By.xpath("//*[@id='Submit']")); if
 * (!submitButtons.isEmpty() && submitButtons.get(0).isDisplayed() &&
 * submitButtons.get(0).isEnabled()) { WebElement clicksavechanges =
 * submitButtons.get(0);
 * wait.until(ExpectedConditions.elementToBeClickable(clicksavechanges));
 * ((org.openqa.selenium.JavascriptExecutor)
 * driver).executeScript("arguments[0].scrollIntoView(true);",
 * clicksavechanges); try { clicksavechanges.click(); } catch (Exception ex1) {
 * System.out.println("Standard click failed for Save Changes. Trying JS click."
 * ); ((org.openqa.selenium.JavascriptExecutor)
 * driver).executeScript("arguments[0].click();", clicksavechanges); } } else {
 * System.out.
 * println("'Submit' button not present or not visible/enabled on the page. Skipping click."
 * ); } }
 * 
 * public void open() { // Implement navigation to the product details page }
 * 
 * public int countProducts() { try {
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
 * "//*[@id='category_product_list']"))); List<WebElement> products =
 * driver.findElements(By.xpath("//*[@id='category_product_list']")); return
 * products.size(); } catch (org.openqa.selenium.TimeoutException e) {
 * System.out.println("category_product_list container not found. Current URL: "
 * + driver.getCurrentUrl()); System.out.println("Page source:\n" +
 * driver.getPageSource()); return 0; } }
 * 
 * 
 * public void clickAddressDropdown() { // TODO Auto-generated method stub
 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
 * WebElement dropdownBtn = null; try { // Try the original XPath dropdownBtn =
 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
 * "//*[@id='shipping_address']/div[2]/button/span"))); } catch (Exception e1) {
 * System.out.
 * println("Dropdown button not found with original XPath. Trying fallback XPath. Current URL: "
 * + driver.getCurrentUrl()); // Try a more general XPath as fallback try {
 * dropdownBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
 * "//*[@id='shipping_address']//button"))); } catch (Exception e2) {
 * System.out.
 * println("Dropdown button not found with fallback XPath. Page source:\n" +
 * driver.getPageSource()); return; // Or throw a custom exception if you want
 * to fail the test } }
 * System.out.println("Attempting to click dropdown button...");
 * dropdownBtn.click(); System.out.println("Dropdown button clicked."); // Try
 * to find the address edit iframe, switch only if present List<WebElement>
 * frames = driver.findElements(By.tagName("iframe")); boolean switched = false;
 * for (WebElement frame : frames) { String name = frame.getAttribute("name");
 * String id = frame.getAttribute("id"); if ((name != null &&
 * name.contains("address")) || (id != null && id.contains("address")) || (id !=
 * null && id.contains("offcanvasRight_for_shippingLabel"))) {
 * driver.switchTo().frame(frame); switched = true; break; } } try { WebElement
 * editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
 * "//*[@id='shipping_address']/div[2]/ul/li[1]/button"))); editBtn.click(); }
 * catch (Exception e) { System.out.
 * println("Edit button not found after clicking dropdown. Page source:\n" +
 * driver.getPageSource()); } if (switched) driver.switchTo().defaultContent();
 * } }
 */