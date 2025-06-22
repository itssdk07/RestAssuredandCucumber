package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DeleteApi {
	static Response response;
	public static void deleteRequest(int userID) {
		RestAssured.baseURI= "https://reqres.in/";
		response = RestAssured.given().header("x-api-key", "reqres-free-v1").when().delete("/api/users/" + userID).then().extract().response();
		
	}
	
	public static int getStatusCode() {
		return response.getStatusCode();
	}

}
