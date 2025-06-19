package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import api.GetAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetSteps {
	Response res;
	
	@When("I fetch the user with ID {int}")
	public void fetchUser(Integer id) {
	res = GetAPI.fetchUserByID(id);
	System.out.println("Response of Get Request " + res.getBody().asPrettyString());
	}
	
	@Then("I verify correct User is visible or not") 
	public void verifyUser() {
		JsonPath json = res.jsonPath();
		
		//Get the Acutal values from the response
		int actualID = json.getInt("data.id");
		String actualFirstName = json.getString("data.first_name");
		String actualLastName = json.getString("data.last_name");
		
		//Assert the values
	assertEquals(actualID, 2, "User ID Mismatched");
	assertEquals(actualFirstName, "Janet", "First Name Mismatched");
	assertEquals(actualLastName, "Weaver", "Last Name Mismatched");
	}
	
	
	@Then("I verify 404 error shows or not")
	public void fetchInvalidUser() {
		System.out.println("Response of Get Request " + res.getBody().asPrettyString()+ "Status code : " + res.getStatusCode());
		assertEquals(res.getStatusCode(),401,"incorrect Status code");
	}
	
	}

