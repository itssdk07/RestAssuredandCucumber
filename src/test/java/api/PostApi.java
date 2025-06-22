package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.http.ContentType.JSON;

public class PostApi {
	
	public static Response createUser(String name, String job) {

		RestAssured.baseURI = "https://reqres.in";
		String body = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";
		return RestAssured.given().header("x-api-key","reqres-free-v1").contentType(JSON).when().body(body).post("/api/users").then().extract().response();
	}

}
