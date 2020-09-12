package com.axiom.tests;
import static org.hamcrest.CoreMatchers.equalTo;
import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.axiom.utils.DataGenerators;
import com.axiom.utils.PropertyManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TestScenario2 {	
	String EmployId=null;
	@BeforeClass
	public void baseurl() {		
		try {
			PropertyManager property=new PropertyManager();
			RestAssured.baseURI = property.readPropertyData("url");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	@Test(dataProvider = "Excel", dataProviderClass = DataGenerators.class,priority=0,description="Validate Status code")
	public void validateStatusCode(HashMap<String,String>testData) throws IOException {
		EmployId=testData.get("EmployeeId");			
		RestAssured.get("/v1/employee/"+EmployId+"").then().assertThat().statusCode(200);
	}
	
	@Test(dataProvider = "Excel", dataProviderClass = DataGenerators.class,description="Validate message")
	public void validateMessage(HashMap<String,String>testData) {
		EmployId=testData.get("EmployeeId");
		RestAssured.get("/v1/employee/"+EmployId+"").then().body("message",equalTo("Successfully! Record has been fetched."));
	}
	
	@Test(dataProvider = "Excel", dataProviderClass = DataGenerators.class,description="Validate status code, message, response data")	
	public void validateResponse(HashMap<String,String>testData) {
		EmployId=testData.get("EmployeeId");
		Response response =given(). log().all().get("/v1/employee/"+EmployId+"").then().log().all().assertThat().statusCode(200).extract().response();
		Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
		Assert.assertEquals(response.jsonPath().getString("data.employee_name"), "Garrett Winters");
		Assert.assertEquals(response.jsonPath().getInt("data.employee_salary"), 170750);
		Assert.assertEquals(response.jsonPath().getInt("data.employee_age"), 63);
		Assert.assertEquals(response.jsonPath().getString("data.profile_image"), "");		
		Assert.assertEquals(response.jsonPath().getString("message"), "Successfully! Record has been fetched.");	
	}

}
