package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String userName, String fName, String lName, String useremail, String pwd, String ph) throws InterruptedException
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUserName(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response res = UserEndpoints.createUser(userPayload);
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	//@Test(priority=3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) throws InterruptedException
	{
		Response res = UserEndpoints.deleteUser(userName);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testGetUserByName(String userName) throws InterruptedException
	{
		Response res = UserEndpoints.readUser(userName);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
