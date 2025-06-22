Feature: I want to test login funtionality

Scenario: Login with valid credentials
    When I send a login request with email "eve.holt@reqres.in" and password "cityslicka"
    Then I should receive a token in the response
    And Status Code should be 200

  Scenario: Login with missing password
    When I send a login request with email "peter@klaven" and no password
    Then Status Code should be 400 
    And It should show error message "Missing password"