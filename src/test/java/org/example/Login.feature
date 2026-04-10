Feature: OrangeHRM Login Functionality

  As a user
  I want to log in to the OrangeHRM system
  So that I can access my dashboard

  Scenario: Successful login with valid credentials
    Given I am on the OrangeHRM login page
    When I enter username "Admin" and password "admin123"
    And I click the login button
    Then I should be redirected to the dashboard

  Scenario: Unsuccessful login with invalid credentials
    Given I am on the OrangeHRM login page
    When I enter username "invalidUser" and password "invalidPass"
    And I click the login button
    Then I should see an error message

  Scenario: Login page accessibility
    Given I navigate to "https://opensource-demo.orangehrmlive.com/"
    Then I should see the login page