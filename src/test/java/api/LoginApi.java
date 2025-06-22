package api;

import static io.restassured.http.ContentType.JSON;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginApi {
	
	static Response response;
	public static void loginValidUser(String mailID, String password) {
		RestAssured.baseURI = "https://reqres.in/";
		String body = "{ \"email\": \"" + mailID + "\", \"password\": \"" + password + "\" }";
		response = RestAssured.given()
				.header("x-api-key", "reqres-free-v1").contentType(JSON).body(body).post("/api/login/").then().extract().response();
	
	}
	
	public static void loginInValidUser(String mailID) {
		RestAssured.baseURI = "https://reqres.in/";
		String body = "{ \"email\": \"" + mailID + "\" }";
		response = RestAssured.given()
				.header("x-api-key", "reqres-free-v1").contentType(JSON).body(body).post("/api/login/").then().extract().response();
	
	}

	public static int getStatusCode() {
		return response.getStatusCode();
	}
	
	public static Response getResponse() {
		return response;
	}
}
