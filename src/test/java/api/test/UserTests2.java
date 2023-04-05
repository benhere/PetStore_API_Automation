package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

@SuppressWarnings("unused")
public class UserTests2 {

	Faker fk ;
	User userPayload;
	
	public org.apache.logging.log4j.Logger log;
	
	@BeforeClass
	public void setupData()
	{
		fk = new Faker();
		userPayload = new User();
		
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUserName(fk.name().username());
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		userPayload.setPassword(fk.internet().password(5, 10));
		userPayload.setPhone(fk.phoneNumber().cellPhone());
	
		//logs
		log = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser() throws InterruptedException
	{
		log.info("********* Creating User *************");
		Response res = UserEndpoints2.createUser(userPayload);
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	
		log.info("*********** User is created  *****************");
	}
	
	@Test(priority=2)
	public void testGetUserByName() throws InterruptedException
	{
		log.info("**************  get the User  ******************");
		Response res = UserEndpoints2.readUser(this.userPayload.getUserName());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 404);
	
		log.info("******************  User is fetched  ********************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() throws InterruptedException
	{
		log.info("****************  Updating User  ***************");
		//update data using payload
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());

		Response res = UserEndpoints2.updateUser(this.userPayload.getUserName(), userPayload);
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);		
	
		//checking data after update
		Response resUpdate = UserEndpoints2.readUser(this.userPayload.getUserName());
		Assert.assertEquals(resUpdate.getStatusCode(), 404);
	
		log.info("******************  User is updated  *******************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() throws InterruptedException
	{
		log.info("*******************  Deleting the User ***************");
		Response res = UserEndpoints2.deleteUser(this.userPayload.getUserName());
		Assert.assertEquals(res.getStatusCode(), 404);
	
		log.info("*******************   User is deleted **********************");
	}
	
}
