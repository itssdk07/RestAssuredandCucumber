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
	int actualStatusCode;
	int actualpageno;
	int actualTotalUsersOnPage;
	int actualUserCount;
	
	@When("I fetch the user with ID {int}")
	public void fetchUser(Integer id) {
	res = GetAPI.fetchUserByID(id);
	Context.latestApiResponse = res;  //AI suggestion for later saving response
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
		assertEquals(res.getStatusCode(),401,"incorrect Status code");
	}
	
	
	@When("I fetch the users from page {int}")
	public void testFetchUsersPage(Integer pageno) {
		res = GetAPI.fetchUsersPage(pageno);
		Context.latestApiResponse = res;  //AI suggestion for later saving response
				//fetch acutal values
				JsonPath json = res.jsonPath();
				actualStatusCode = res.getStatusCode();
				actualpageno = json.getInt("page");
				actualUserCount = json.getList("data").size();
		
	}
	
	@Then("I should get a list of users with page number {int}")
	public void verifyPageNo(Integer pageno) {
		assertEquals(actualpageno,pageno,"Page Number is mismatched");
		
	}
	
	@Then("Status code should be {int}")
	public void verifyStatusCode(Integer statuscode) {
		assertEquals(actualStatusCode, statuscode, "Status code Mismatched");
	}
	
	@Then("User list should not be empty")
	public void verifyUserList() {
		assertTrue(actualUserCount>0, "User list is empty");
	}
	
	
	}

