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
	@When("I fetch the user")
	public void fetchUser() {
	res = GetAPI.fetchUserByID(2);
	}
	
	@Then("I verify correct User is visible or not") 
	public void verifyUser() {
		JsonPath json = res.jsonPath();
		int id = json.getInt("data.id");
		String firstName = json.getString("data.first_name");
	assertEquals(id, 2);
	assertEquals(firstName, "Janet");
		
	
	}
}
