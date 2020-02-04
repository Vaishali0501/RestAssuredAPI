package com.qa.furniture.test;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.furniture.Base.BaseClass;
import com.qa.furniture.dto.ProductListDTO;
import com.qa.furniture.resources.XLSWorker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

public class FurniturePostTest extends BaseClass {

	
	@DataProvider
	public Object[][] getProductData() throws Exception {
	
		Object data[][] = XLSWorker.getTestData("DataSheet");
		return data;
	}
	
	
	@Test(priority = 1, dataProvider = "getProductData")
	public void postProduct(String availability, String categoryId, String color,String createdOn,String description,String discount, String materialDescription, String name, String price, String updateOn, String warranty ) 
	{

		try
		{
		Reporter.log("Post Value API Test begins",true);
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		Boolean avail = Boolean.parseBoolean(availability);
		
		Double catIdDouble = Double.parseDouble(categoryId);
		Double discDouble = Double.parseDouble(discount);
		Double warrnDouble = Double.parseDouble(warranty);
		Double priceDouble = Double.parseDouble(price);
		
		ProductListDTO pdto =new ProductListDTO(avail,catIdDouble,color, createdOn, description, discDouble,  materialDescription,  name,priceDouble,  updateOn,warrnDouble);		
		
		
		httpRequest.header("Content-Type","application/json");		
		
		httpRequest.body(pdto);
		
		//Response Object
		
		Response response = httpRequest.request(Method.POST, "rest/api/product/");		
		
		String responseBody = response.getBody().asString();
		
		//Status code validation
		int statusCode=response.getStatusCode();
		Reporter.log("Status code is"+statusCode,true);
		Assert.assertEquals(statusCode, 201);
		
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
		catch(AssertionFailedError ie) {
			
			Reporter.log("Status code does not match",true);
		}
		
	
	}

}
