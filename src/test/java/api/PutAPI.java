package api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class PutAPI {

	public static Response updateUserDetails(int ID, String name) {
		String body =  "{ \"name\": \"" + name + "\" }";
		RestAssured.baseURI = "https://reqres.in/api/users/" + ID;
		Response response = RestAssured.given().header("x-api-key", "reqres-free-v1").contentType(JSON).body(body).when().put().then().extract().response();
		return response;
	}
	
	public static Response updateUserDetails(int ID, String name, String job) {
		String body = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";
		RestAssured.baseURI = "https://reqres.in/api/users/" + ID;
		Response response = RestAssured.given().header("x-api-key", "reqres-free-v1").contentType(JSON).body(body).when().put().then().extract().response();
		return response;
	}
	
}
