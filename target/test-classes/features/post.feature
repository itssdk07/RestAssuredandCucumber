Feature: I want to create new user and verify it has been created properly 

Scenario: Create a new user with name and job
    When I send a POST request with name "morpheus" and job "leader"
    Then the user should be created with status code 201
    And Created User name should be "morpheus"
    And Created user job should be "leader"
    
    