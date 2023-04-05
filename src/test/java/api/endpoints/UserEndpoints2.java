package api.endpoints;

import static io.restassured.RestAssured.given;
import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
 * ## UserEndpoints.java
==> Created to perform CRUD operations
 */

public class UserEndpoints2 {
	
	// method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		//load properties file using ResourceBundle class
		ResourceBundle rb = ResourceBundle.getBundle("routers");
		return rb;
	}
	
	public static Response createUser(User payload) throws InterruptedException
	{
		String post_url = getURL().getString("post_url");
		
		Response res = given()
		                   .contentType(ContentType.JSON)
		                   .accept(ContentType.JSON)
		                   .body(payload)
		
		               .when()
		                   .post(post_url);
		Thread.sleep(2000);
		
		return res;
	}

	public static Response readUser(String userName) throws InterruptedException
	{
		String get_url = getURL().getString("get_url");
		
		Response res = given()
		                   .pathParam("username", userName)
		
		               .when()
		                   .get(get_url);
		Thread.sleep(2000);
		return res;
	}
	
	public static Response updateUser(String userName, User payload) throws InterruptedException
	{
		String update_url = getURL().getString("update_url");
		
		Response res = given()
		                   .contentType(ContentType.JSON)
		                   .accept(ContentType.JSON)
		                   .pathParam("username", userName)
		                   .body(payload)
		
		               .when()
		                   .put(update_url);
		Thread.sleep(2000);
		return res;
	}
	
	public static Response deleteUser(String userName) throws InterruptedException
	{
		String delete_url = getURL().getString("delete_url");
		
		Response res = given()
		                   .pathParam("username", userName)
		
		               .when()
		                   .get(delete_url);
		
		Thread.sleep(2000);
		return res;
	}
	
}
