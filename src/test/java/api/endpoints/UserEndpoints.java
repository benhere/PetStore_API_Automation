package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
 * ## UserEndpoints.java
==> Created to perform CRUD operations
 */

public class UserEndpoints {
	
	public static Response createUser(User payload) throws InterruptedException
	{
		Response res = given()
		                   .contentType(ContentType.JSON)
		                   .accept(ContentType.JSON)
		                   .body(payload)
		
		               .when()
		                   .post(Routes.post_url);
		Thread.sleep(2000);
		
		return res;
	}

	public static Response readUser(String userName) throws InterruptedException
	{
		
		Response res = given()
		                   .pathParam("username", userName)
		
		               .when()
		                   .get(Routes.get_url);
		Thread.sleep(2000);
		return res;
	}
	
	public static Response updateUser(String userName, User payload) throws InterruptedException
	{
		
		Response res = given()
		                   .contentType(ContentType.JSON)
		                   .accept(ContentType.JSON)
		                   .pathParam("username", userName)
		                   .body(payload)
		
		               .when()
		                   .put(Routes.update_url);
		Thread.sleep(2000);
		return res;
	}
	
	public static Response deleteUser(String userName) throws InterruptedException
	{
	
		Response res = given()
		                   .pathParam("username", userName)
		
		               .when()
		                   .get(Routes.delete_url);
		
		Thread.sleep(2000);
		return res;
	}
	
}
