package stepDefinitions;

import static org.testng.Assert.assertEquals;

import api.PostApi;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostSteps {
	Response response;
	int actualStatusCode;
	String actualName;
	String actualJob;

	@When("I send a POST request with name {string} and job {string}")
	public void createNewUser(String name, String job) {
		response = PostApi.createUser(name, job);
		Context.latestApiResponse = response;  //AI suggestion for later saving response
		JsonPath json = response.jsonPath();
		
		//Actual values
		
		actualStatusCode = response.getStatusCode();
		actualName = json.getString("name");
		actualJob = json.getString("job");
		
	}
	
	   //Assertions
	
	@Then("the user should be created with status code {int}")
	public void testStatusCode(Integer statusCode) {
		assertEquals(actualStatusCode, statusCode,"Status Code Mismatched");
	}
	
	@Then("Created User name should be {string}")
	public void testNewUserName(String name) {
		assertEquals(actualName,name,"Username is mismatched");
	}
	
	@Then("Created user job should be {string}")
	public void testNewUserJob(String job) {
		assertEquals(actualJob,job,"Job is Mismatched");
	}
			
	
}
