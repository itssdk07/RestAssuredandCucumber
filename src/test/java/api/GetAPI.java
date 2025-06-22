package api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAPI {

	public static Response fetchUserByID(int id) {
		RestAssured.baseURI = "https://reqres.in/api/users/" + id;
		return RestAssured.given().when().get().then().extract().response();
		
	}
	
	public static Response fetchUsersPage(int pageno) {
		RestAssured.baseURI = "https://reqres.in";
		return RestAssured.given().queryParam("page",pageno).when().get("api/users").then().extract().response();
	}
}
