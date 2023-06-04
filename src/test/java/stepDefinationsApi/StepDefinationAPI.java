package stepDefinationsApi;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinationAPI extends Utils {
	public ResponseSpecification respec;
	public Response response;
	
	//****** By putting static keyword it accessible for another method tooooo.
	static String place_id;   // from getting place id verify step
	// TestDataBuild data=new TestDataBuild();
	 
	public RequestSpecification res;
	TestDataBuild data = new TestDataBuild();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		System.out.println("this is pre requirement Given Step");

		// Below Code is Move To Source package Util Class

		// RestAssured.baseURI="http://rahulshettyacademy.com";
		// RequestSpec build return type is requestSpecifications
		/*
		 * RequestSpecification req=new
		 * RequestSpecBuilder().setBaseUri("http://rahulshettyacademy.com").
		 * addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
		 * RestAssure.BaseURI="URL of Request"; Responsespecbuilder return type is
		 * responseSpecifications respec=new
		 * ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.
		 * JSON).build();
		 */
		
		res = given().spec(requestSpecification()) // this argument method from Util class
				.body(data.addPlacePayLoad(name,language,address));

	}

//	@When("^user calls \"([^\"]*)\" with post http request$")
//	public void user_calls_with_post_http_request(String strArg1) throws Throwable {
//		
//		//if(strArg1=="addplace") {
//			
//		// here enumclass constructor got executed add return value of AddPlaceAPI from Enum class	
//	APIResources resourceAPI =APIResources.valueOf("AddPlaceAPI");
//	System.out.println(resourceAPI.getResources());
//	// api resource class name with reference name and calling of constructor.	
//		
//	//	}
//		System.out.println("this is  When Step Mean Actual Condition");
//		respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//
//		//response = res.when().post("/maps/api/place/add/json").then().spec(respec).extract().response();
//		/* 1.instead of above line calling with direct endpoint we are using Enum class with name APIResource
//		 * 2. create few variables with different value.
//		 * 3. and we created a constructor there with parameter resource and make this local with this keyword.
//		 * public enum APIResources {
//******* BELOW ONE IS ENUM CLASS CODE ***********
//AddPlaceAPI("/maps/api/place/add/json"),
//getPlaceAPI("/maps/api/place/get/json"),
//deletePlaceAPI("/maps/api/place/delete/json");
//	
//private String resource;
//	APIResources(String resource){
//		this.resource=resource;
//		}
//	public String getResources() {
//		return resource;
//	}   */
//		
//		response = res.when().post(resourceAPI.getResources()).then().spec(respec).extract().response();
//
//	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) throws Throwable {
	
	//	constructor will be called with value of resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
	System.out.println(resourceAPI.getResources());
	
    if(method.equalsIgnoreCase("POST")) {
    	System.out.println(method+ " :http method what we doing right now");
	response = res.when().post(resourceAPI.getResources());
	//.then().spec(respec).extract().response(); our goal just request

    }else if(method.equalsIgnoreCase("GET")) {
    	System.out.println("GEt Method going to be Executed");
    	response=res.when().get(resourceAPI.getResources());
    	System.out.println("successfullly done with Get Method");
    }
		}

	
	
	@Then("^validate the API call success with status code \"([^\"]*)\"$")
	public void validate_the_api_call_success_with_status_code(String strArg1) throws Throwable {
		System.out.println("this is post Requirement we can do Validations");
		assertEquals(response.getStatusCode(), 200);
		

	}

	
	 @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$") 
	 public void response_body_is(String keyValue, String ExpectedValue) {
	 System.out.println("response is should be 200 Ok"); 
	 
	 assertEquals(getJsonPath(response,keyValue),ExpectedValue);
	  
	  
	   
	   
	   
	   //assertEquals("OK",ExpectedValue);
	  }
	 
	 
		/*
		 * public void in_response_body_is(String keyValue, String Expectedvalue) { //
		 * Write code here that turns the phrase above into concrete actions
		 * 
		 * // assertEquals(getJsonPath(response,keyValue),Expectedvalue); }
		 */




    @And("verify place_Id created maps to {string} using {string}")
    public void verify_placeid_created_maps_to_using(String expectedName , String resource) throws Throwable {
    	
    	place_id=	getJsonPath(response,"place_id");
    	System.out.println(place_id);
    	res=given().spec(requestSpecification()).queryParam("place_id", place_id);
    	
    	user_calls_with_http_request(resource, "GET"); // calling predefined method in this same class
    	
    	String actualName=getJsonPath(response,"name");
    	System.out.println(actualName);
    	assertEquals(actualName, expectedName);
    	
    
    }
    @Given("DeletePlace payload")
    public void deleteplace_payload() throws Throwable {
    	System.out.println("deletePlace Payload Given Test");
    	res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    	
    }

    
}
      