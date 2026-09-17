@ui
Feature: OrangeHRM Employee Management

  Background:
    Given the user is on the OrangeHRM login page
    When the user logs in with username "Admin" and password "admin123"
    Then the Dashboard should be displayed

  @regression
  Scenario: Add a new employee and verify the generated employee ID
    When the user navigates to the PIM module
    And the user adds a new employee with first name "Automation" and last name "User"
    Then the employee should be created successfully
    And the employee should be searchable using the generated employee ID