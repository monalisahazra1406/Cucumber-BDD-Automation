Feature: OrangeHRM Login

  Scenario: Login with valid credentials
    Given the user is on the OrangeHRM login page
    When the user logs in with valid credentials
    Then the Dashboard should be displayed


  Scenario: Login with invalid credentials
    Given the user is on the OrangeHRM login page
    When the user logs in with invalid credentials
    Then an invalid credentials message should be displayed