package api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAPI {

	public static Response fetchUserByID(int id) {
		RestAssured.baseURI = "https://reqres.in/api/users/" + id;
		return RestAssured.given().when().get().then().extract().response();
		
	}
}
