@tag
Feature: Error Validation

  @ErrorValidation
  Scenario Outline: Negative test for login error validation
    Given I landed on the Ecommerce Page
    When Logged in with username <name> and password <password>
    But "Incorrect email or password." message is displayed

    Examples: 
      | name  								  | password          |
      | wrongUserName@test.com  | wrong password    |
