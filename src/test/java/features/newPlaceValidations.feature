Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
	Given Add place payload with "<name>" "<language>" "<address>"
	# here is arrow indication that columns in table
	#	When user calls "AddPlaceAPI" with post http request
	When user calls "AddPlaceAPI" with "POST" http request
	#When user calls "getPlaceAPI" with "GET" http request
	Then validate the API call success with status code "OK"
	And "status" in response body is "OK"
  And "scope" in response body is "APP" 
  Then verify place_Id created maps to "<name>" using "getPlaceAPI"
  # here test step with    <> its from table  "text" its consider as argument
  
  
	 
Examples:
 		|name    | language | address   |
  	|manoj tiwari	| kannada	 | Tutuikudi |
  #	|krishna | Malayalam| Ernakulam |
 @deletePlace	
 Scenario Outline: Verify if Delete place functionality is working
 	Given DeletePlace payload
 	When user calls "deletePlaceAPI" with "DELETE" http request
	Then validate the API call success with status code "OK"
	And "status" in response body is "OK"
 	
	
 