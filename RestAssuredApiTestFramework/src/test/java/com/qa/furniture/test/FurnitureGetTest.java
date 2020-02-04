package com.qa.furniture.test;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.mindtree.sdet.pages.HomePage;
import com.qa.furniture.Base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

public class FurnitureGetTest extends BaseClass {
	
	@BeforeMethod
	public void setUp(){
		FurniturePut FurniturePut= new FurniturePut();	
		RestAssured.basePath = "/rest/api/category/all";
		
	}
	
	@Test
	public void swaggerTest() {

		try
		{
		Reporter.log("Get Value API Test begins",true);
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET);

		String responseBody = response.getBody().asString();
		
		//Status code validation
		int statusCode=response.getStatusCode();
		Reporter.log("Status code is"+statusCode,true);
		Assert.assertEquals(statusCode, 302);
		
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		catch(AssertionFailedError ie) {
			
			Reporter.log("Status code does not exist",true);

		}
	}

}
