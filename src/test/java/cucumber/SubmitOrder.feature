
@tag
Feature: Purchase the order from ecommerce website
  
  Background:
  Given I landed on Ecommerce page

  
  @Regression
  Scenario Outline: Positive test of submitting the order
    Given I logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and Submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name  								| password 			| productName  |
      | shantshetty@gmail.com | Shetty@123 		| ZARA COAT 3	 |
      