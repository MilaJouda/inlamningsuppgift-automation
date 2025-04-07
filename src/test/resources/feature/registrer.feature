Feature: user Registration

  Scenario: Successful registration
    Given   I am on the registration page
    When    I fill in all required member details fields correctly
    And     I accept the terms and conditions
    And     I confirm I am aged over 18
    And     I agree on the code of ethics
    And     I click "CONFIRM AND JOIN"
    Then    I should see the message "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"

  Scenario: Missing last name
    Given   I am on the registration page
    When    I fill in all required member details fields correctly except last name
    And     I accept the terms and conditions
    And     I confirm I am aged over 18
    And     I agree on the code of ethics
    And     I click "CONFIRM AND JOIN"
    Then    I should see the message "Last Name is required"

  Scenario: Password do not match
    Given   I am on the registration page
    When    I fill in all required member details fields with mismatched passwords
    And     I accept the terms and conditions
    And     I confirm I am aged over 18
    And     I agree on the code of ethics
    And     I click "CONFIRM AND JOIN"
    Then    I should see the message "Password did not match"

  Scenario: Terms and conditions not accepted
    Given   I am on the registration page
    When    I fill in all required member details fields correctly
    And     I confirm I am aged over 18
    And     I agree on the code of ethics
    And     I click "CONFIRM AND JOIN"
    Then    I should see the message "You must confirm that you have read and accepted our Terms and Conditions"


  Scenario Outline: Registration on Different Browsers
    Given   I am on the registration page using "<browser>"
    When    I fill in the form with <birthDate>, <firstName>, <lastName>, <password>, <confirmPassword>
    And     I accept the terms and conditions
    And     I confirm I am aged over 18
    And     I agree on the code of ethics
    And     I click "CONFIRM AND JOIN"
    Then    I should see the message "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"

    Examples:
      | browser | birthDate  | firstName | lastName | password     | confirmPassword |
      | chrome  | 01/01/1990 | Test      | User     | Password123! | Password123!    |
      | firefox | 01/01/1990 | Test      | User     | Password123! | Password123!    |
      | chrome  | 12/12/1982 | Web       | Driver   | Password321! | Password321!    |
      | firefox | 12/12/1982 | Web       | Driver   | Password321! | Password321!    |





