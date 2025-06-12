package api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAPI {

	public static Response fetchUserByID(int id) {
		return given() 
				 .baseUri("https://reqres.in")
				.when().get("/api/users/" + id);
		
	}
}
