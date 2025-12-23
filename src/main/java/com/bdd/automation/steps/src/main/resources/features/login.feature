@Login
Feature: User Login Functionality
  As a user
  I want to login to the application
  So that I can access my account

  Background:
    Given User navigates to the login page

  @sanity @positive
  Scenario: User successfully logs in with valid credentials
    When User enters username as "testuser" and password as "Test@1234"
    And User clicks on the login button
    Then User should be logged in successfully

  @sanity @positive
  Scenario Outline: User login with multiple valid credentials
    When User enters username as "<username>" and password as "<password>"
    And User clicks on the login button
    Then User should be logged in successfully

    Examples:
      | username  | password   |
      | user1     | pass@1234  |
      | user2     | pass@5678  |
      | testadmin | admin@2024 |

  @negative
  Scenario: User login fails with invalid credentials
    When User enters username as "invaliduser" and password as "wrongpassword"
    And User clicks on the login button
    Then User should see error message

  @negative
  Scenario: User login fails with empty username
    When User enters username as "" and password as "Test@1234"
    And User clicks on the login button
    Then User should see error message

  @regression
  Scenario: User login fails with empty password
    When User enters username as "testuser" and password as ""
    And User clicks on the login button
    Then User should see error message
