package com.qa.furniture.test;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.furniture.Base.BaseClass;
import com.qa.furniture.resources.XLSWorker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

public class FurniturePut extends BaseClass{
	
	BaseClass bc= new BaseClass();
	
	@BeforeMethod
	public void setUp(){
		FurniturePut FurniturePut= new FurniturePut();	
		RestAssured.basePath = "/rest/api/product";
		
	}

	
	@DataProvider
	public Object[][] getProductData() throws Exception {
	
		Object data[][] = XLSWorker.getTestData("PutValue");
		return data;
	}
	
	
	@Test(priority = 1, dataProvider = "getProductData")
	public void putTest(String putValue)
	{
		try
		{
		Reporter.log("Put Value API Test begins",true);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json");				
		JSONObject json = new JSONObject();
		
		float f = Float.valueOf(putValue.trim()).floatValue();
		int pID= (int) f;
		
		json.put("id",pID);
		request.body(json.toJSONString());
		//String url = bc.URLs();
	//	String newURL = url+"/rest/api/product/"+pID;
		Response response = request.put("/"+pID);
		
		
		int Code = response.getStatusCode();
		
		System.out.println("response code"+Code);
		
		Assert.assertEquals(Code, 200);					
	
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		catch(AssertionFailedError ie) {
			
			System.out.println("Status code does not match");

		}

}
	}
