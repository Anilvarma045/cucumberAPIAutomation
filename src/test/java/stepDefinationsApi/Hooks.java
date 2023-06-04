package stepDefinationsApi;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@deleteplace")
	public void beforeScenario() throws Throwable {
		
		// write a code to get PlaceId
		// execute this code only when placeId get Null
		StepDefinationAPI sdf=new StepDefinationAPI();
	
		if(StepDefinationAPI.place_id==null) {  // place_id static bcoz call with className
			 System.out.println("The place_id is null so im going to execute below code");
		
		
		sdf.add_place_payload("ramu", "korien", "telugu");
		sdf.user_calls_with_http_request("AddPlaceAPI", "POST");
		sdf.verify_placeid_created_maps_to_using("ramu","getPlaceAPI");
	}
	}
	
}
