package stepDefinitions;

import static org.testng.Assert.assertEquals;

import api.DeleteApi;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class DeleteSteps {
	
	
	
	@When("I send a DELETE request for user ID {int}")
	public void sendDeleteRequest(int UserID) {
		DeleteApi.deleteRequest(UserID);
	}

	
	@Then("I should receive status code {int}")
	public void testUserDeleted(int statusCode) {
		int actualStatusCode = DeleteApi.getStatusCode();
		assertEquals(actualStatusCode, statusCode,"Status Code Mismatched");
	}
}
