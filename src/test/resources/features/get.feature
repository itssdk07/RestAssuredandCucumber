Feature: Fetching user and verification


Scenario: Fetch a correct user
When I fetch the user with ID 2
Then I verify correct User is visible or not


Scenario: Fetch a incorrect user
When I fetch the user with ID 23
Then I verify 404 error shows or not

Scenario: Fetch list of users on page 2
    When I fetch the users from page 2
    Then I should get a list of users with page number 2
    And Status code should be 200
    And User list should not be empty