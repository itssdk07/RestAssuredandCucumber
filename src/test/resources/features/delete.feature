Feature: I want to test delete request 

Scenario: Delete a user by ID
    When I send a DELETE request for user ID 2
    Then I should receive status code 204