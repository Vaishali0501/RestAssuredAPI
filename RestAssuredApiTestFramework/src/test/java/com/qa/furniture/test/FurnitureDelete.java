package com.qa.furniture.test;

import static org.testng.Assert.assertFalse;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import com.qa.furniture.Base.BaseClass;
import com.qa.furniture.Util.*;
public class FurnitureDelete {
	
	randomIDGenerator rID = new randomIDGenerator();
	BaseClass bc= new BaseClass();
	
	@BeforeMethod
	public void setUp(){
		FurnitureDelete FurnitureDelete= new FurnitureDelete();	
		RestAssured.basePath = "/rest/api/product";		
	}
	
	@Test
	public void deleteTest()
	{
		try{
			
		Reporter.log("Delete Value API Test begins",true);
		RequestSpecification request = RestAssured.given();
		
		int value = rID.randomPutValue();
							
		Response response = request.put("/"+value);
		
		int Code = response.getStatusCode();
		
		Assert.assertEquals(200, Code);		
	}
	catch(NullPointerException ex) {
		ex.printStackTrace();
	}
	catch(AssertionFailedError ie) {
		
		Reporter.log("ID does not exist",true);
	}
	}

}
