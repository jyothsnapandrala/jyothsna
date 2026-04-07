@onlinebrandstore
Feature: Login to Capgemini Brand Store

  #@signin
  #Scenario: sign up with capgemini email and set a new password
   # Given I open on the Capgemini Brand Store website
    #When I enter Capgeimini email ID in signup form
    #When I click on the Login/Register button
    #Then I should enter the Received OTP
    #And I set a new password and confirm the password
    #Then I have to click the Submit button
 
  @login
   Scenario: Successful login
    Given I am on the login page
    When I enter valid email 
    And I click the login button
    Then I should enter the OTP
    Then I enter the password
    Then I have click the login button
    
  # @Neglogin
   # Scenario: UnSuccessful login
    # Given I am in the login page
     #When I leave the email field blank
     #Then I click login button
     #Then I should see a validation message "This field is required."
   
  @partialText
  Scenario: Displaying bag-related suggestions in the search bar
  # Given the user is on the homepage of the Capgemini website
   When the user search for an item fetched from the database
   Then the system scroll down in the search dropdown
   And the user should be able to validate the  items
   
   

  #@checkout
  #Scenario: User fills address details from database and completes checkout
   # Given user is on the checkout page
    #When user clicks the address dropdown
    #Then user fills address details from database and saves changes
    #And user clicks continue to complete the order
    
   #@searchingproducts
   #Scenario: User searches for products and finds the highest and lowest price
    #Given the user is on the advanced search page with search term 
    #When the user navigates through all result pages
    #When the user collects all product prices and compare highest price and lowest price
    #When user clicks the address dropdown
    #Then user fills address details from database and saves changes
    

 @negativeaddress
  Scenario: Show validation error when First Name is empty in address form
    Given user is on the advanced search page with search term 
    When I on the user navigates through all result pages
    When I  collects all product prices and compare highest price and lowest price
    When I have clicks the address dropdown
    Then I have fills address details from database and saves changes
    Then I should see an error message for the incorrect pincode
    
    