Feature:ProfileUpdation
Background:
   Given I go to the LinkedIn login page
   When I enter username and password and click on sign in button
   When I go to the view profile page
#
#Scenario:updating the headline
    #When I click on Editicon and enter "<headline>" 
    #Then I click on save button and headline is saved successfully
   #Examples:
    #|headline                   |
    #|Software engineer          |
    #|Automation Selenium testing|
    #|Data Scientist             |
#
#Scenario:Displaying all skills in Profile
    #When I go to Skills field
    #Then I click on All Skills Present and display one Skill
#
#Scenario:Saving an Emptyfield
    #When I click on editicon of School
    #And I click on save button
    #Then I should see an "error"
#
#Scenario:Go to Saveditems Page
    #When I click on Resources and then SavedItems button
    #Then It should navigate to SavedItems page

Scenario:Navigate to Public Profile and URL Settings page
    When I click on editicon of Public Profile and URL
    Then I navigated to new tab with change of URL
    
   
    
    #
    #
    #
#Scenario:updating the headline
    #When I click on Editicon and enter "<headline>" 
    #Then I click on save button and headline is saved successfully
  #Examples:
    #|headline                   |
    #|Software engineer          |
    #|Automation Selenium testing|
    #|Data Scientist             |
    #
    
    
    
    
    
    
    
    
    
    
