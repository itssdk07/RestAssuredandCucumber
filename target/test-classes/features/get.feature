Feature: Fetching user and verification


Scenario: Fetch a correct user
When I fetch the user with ID 2
Then I verify correct User is visible or not


Scenario: Fetch a incorrect user
When I fetch the user with ID 23
Then I verify 404 error shows or not



