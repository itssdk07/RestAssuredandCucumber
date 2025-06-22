package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import api.LoginApi;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LoginSteps {
	Response response;
	@When("I send a login request with email {string} and password {string}")
	public void loginWithValidUser(String mailID, String pwd) {
		LoginApi.loginValidUser(mailID, pwd);
		response = LoginApi.getResponse();
		Context.latestApiResponse = response;  //AI suggestion for later saving response
	}
	
	@Then("I should receive a token in the response")
	public void testResponseToken() {
		
		JsonPath json = response.jsonPath();
		
		String actualToken = json.getString("token");
		assertTrue(actualToken != null, "token is null");
	}
	
	@Then("Status Code should be {int}")
	public void testStatusCode(Integer statusCode) {
		int actualStatusCode = response.getStatusCode();
		assertEquals(actualStatusCode, statusCode, "Status Code Mismatched");
	}
	
	@When("I send a login request with email {string} and no password")
	public void LoginWithInvalidUser(String mailID) {
		LoginApi.loginInValidUser(mailID);
		response = LoginApi.getResponse();
		Context.latestApiResponse = response;  //AI suggestion for later saving response
	}
	
	@Then("It should show error message {string}")
	public void testErrorMessage(String errorMessage) {
		JsonPath json = response.jsonPath();
		String actualErrorMessage = json.getString("error");
		assertEquals(actualErrorMessage, errorMessage, "Error Message Mismatched");
	}
	
	
}
