Feature: user Registration

  Scenario: Successful registration
    Given   I am on the page
    When    I fill in all required fields correctly
    And     I accept the terms and conditions
    And     I click  "Create Supporter Account"
    Then    I should see the message "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND "

   Scenario: Missing last name
    Given   I am on the page
     When   I fill in the fields without a last name
     And    I accept the terms and conditions
     And    I click  "Create Supporter Account"
     Then   I should see the message "last name is required"

  Scenario: Password do not match
    Given   I am on the page
    When    I fill in the fields with mismatched passwords
    And     I accept the terms and conditions
    And     I click  "Create Supporter Account"
    Then    I should see the message "Password do not match"

    Scenario: Terms not accepted
      Given  I am on the page
      When   I fill in all required fields correctly
      And    I click  "Create Supporter Account"
      Then   I should see the message " You must accept the terms and conditions first"
