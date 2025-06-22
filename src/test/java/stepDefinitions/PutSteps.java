package stepDefinitions;

import static org.testng.Assert.assertEquals;

import api.PutAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PutSteps {
	Response response;
	JsonPath json;
	int actualStatusCode;
	int actualID;
	String actualName;
	String actualJob;
	
	//Scenario: Update user name of the valid User
	
	@When("Update name of the user {int} as {string}")
	public void testUpdateUserName(Integer ID, String name) {
		response = PutAPI.updateUserDetails(ID, name);
		Context.latestApiResponse = response;  //AI suggestion for later saving response
		json = response.jsonPath();
		
		//get actual values
		actualStatusCode = response.getStatusCode();
		actualID = ID;
		actualName = json.getString("name");
	}
	
		//Assertions
		
		@Then("Verify response status code is {int}")
		public void verifyStatusCode(Integer statusCode) {
			assertEquals(actualStatusCode, statusCode, "Unexpected Status Code");
		}
		
		@Then("Verify user ID is {int}") 
		public void verifyUserID(Integer ID) {
			assertEquals(actualID, ID, "User ID Mismatched");
		}
		
		@Then("Verify Name of the User is {string}")
		public void verifyUserName(String name) {
			assertEquals(actualName,name,"User Name Mismatched");
		}
		
		
		//Scenario: Update name and job of the valid user at a time
		
		
		@When("Update name of the user {int} as {string} and job as {string}")
		public void testUpdateNameJob(Integer ID, String name, String job) {
			response = PutAPI.updateUserDetails(ID, name, job);
			
			Context.latestApiResponse = response;  //AI suggestion for later saving response
			
			json = response.jsonPath();
			
			//get Actual data
			
			actualStatusCode = response.getStatusCode();
			actualID = ID;
			actualName = json.getString("name");
			actualJob = json.getString("job");
		}
	
		@Then("Job of the user is {string}")
		public void verifyJob(String job) {
			assertEquals(actualJob,job,"Job is Mismatched");
		}
		
	}
