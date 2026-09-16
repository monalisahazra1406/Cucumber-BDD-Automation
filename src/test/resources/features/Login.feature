Feature: OrangeHRM Login

  Background:
    Given the user is on the OrangeHRM login page

    @smoke @regression
  Scenario: Login with valid credentials
    When the user logs in with valid credentials
    Then the Dashboard should be displayed

    @regression
  Scenario Outline: Login with invalid credentials
    When the user logs in with username "<username>" and password "<password>"
    Then an invalid credentials message should be displayed

    Examples:
      | username  | password      |
      | Admin     | wrongPassword |
      | wrongUser | admin123      |

  @smoke @regression
  Scenario: Login using DataTable
    When the user logs in with the following credentials
      | username | Admin    |
      | password | admin123 |
    Then the Dashboard should be displayed

    @smoke @regression
  Scenario: Read multiple credential records using DataTable
    Given the following login credential records are available
      | username  | password      |
      | Admin     | wrongPassword |
      | wrongUser | admin123      |