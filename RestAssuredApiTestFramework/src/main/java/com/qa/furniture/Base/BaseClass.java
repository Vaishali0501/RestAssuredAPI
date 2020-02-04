package com.qa.furniture.Base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.Reporter;

public class BaseClass {
	
	//URLs for the functions
	
	@BeforeClass
	//public String URLs()
	//{
	//	RestAssured.baseURI = "http://okmry52647dns.eastus.cloudapp.azure.com:8089";
	//	return RestAssured.baseURI;
	
	//}
	
	 public void beforeClass(ITestContext context) {
	        RestAssured.baseURI = context.getCurrentXmlTest().getParameter("BaseURI");
	        Reporter.log("BaseURI Set", true);

}
}
