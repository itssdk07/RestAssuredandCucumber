Feature: I want to update an existing user name using PUT method So that I can verify the user data is correctly updated

Scenario: Update user name of the valid User
When Update name of the user 2 as "APItest"
Then Verify response status code is 200
And Verify user ID is 2
And Verify Name of the User is "APItest"


Scenario: Update name and job of the valid user at a time
When Update name of the user 2 as "QAAnalyst" and job as "APItesting"
Then Verify user ID is 2
And Verify response status code is 200
And Verify Name of the User is "QAAnalyst"
And Job of the user is "APItesting"
