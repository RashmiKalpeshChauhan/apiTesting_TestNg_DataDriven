package com.axiom.tests;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.axiom.utils.PropertyManager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestScenario1 {
	@BeforeClass
	public void baseurl() {		
		try {
			PropertyManager property=new PropertyManager();
			RestAssured.baseURI = property.readPropertyData("url");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	@Test(description="Validate status code")
	public void validateStatusCode() {
		get("/v1/employees").then().assertThat().statusCode(200);
	}
	@Test(description="Validate status code and profile image should be empty")
	public void validateProfileImageEmpty() {
		Response response =given(). log().all().get("/v1/employees").then().log().all().assertThat().statusCode(200).extract().response();
		int test=response.jsonPath().getString("data.profile_image").length();
		for (int i = 1; i < test; i++) {	
			Assert.assertTrue(response.jsonPath().getString("data.profile_image["+i+"]").isEmpty());			
		}
	}
	
}
