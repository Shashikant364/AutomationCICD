
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative test for validating email and password
    Given I landed on Ecommerce page
    When I logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  								| password 			| 
      | shantshetty@gmail.com | Shetty@12			|
