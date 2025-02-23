#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios. (High level senarios).
#Scenario: Business rule through list of steps with arguments (Test Case).
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps. But can be used for negative test case.
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template


@tag
Feature: Purchase Order from Ecommerce Website.

  Background:                                        ##pre-condition
  Given I landed on the Ecommerce Page


  @Purchase_in_India
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username <name> , email <email>  and password <password>
    When I add mobile <mobilename> to the cart
    And  I add country <country>
    Then I verify the success message "Success! Thank you! Your order"         
##providing data directly in then statement instead of table.
    Examples: 
      | name               | email      | password  | mobilename     | country |
      | Harsh Vibhor Singh |thug@tcs.com| thug      |   Nokia Edge   | India   |

      
  @Purchase_in_China
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username <name> , email <email>  and password <password>
    When I add mobile <mobilename> to the cart
    And  I add country <country>
    Then I verify the success message "Success! Thank you! Your order"           
##providing data directly in then statement instead of table.
    Examples: 
      | name               | email      | password  | mobilename     | country |
      | Harsh Vibhor Singh |thug@tcs.com| thug      |   Blackberry   | China  |
      
      
      
      