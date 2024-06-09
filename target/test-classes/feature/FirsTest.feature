#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

@tag
Feature: Validating place api's 

  @AddPlace
  Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<Language>" "<Address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the api call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
		And verify place_id created maps to "<name>" using "getPlaceAPI"
Examples:
| name  		 | Language | 			 Address  	 |
| SonuHouse  | English	| worls cross center |
#| KajalHouse | French	  |  Sea cross center|

@DeletePlace
Scenario: Verify if Delete Place functionality is working
Given fetch the "place_id"
When user calls "deletePlaceAPI" with "POST" http request
Then the api call got success with status code 200
And "status" in response body is "OK"
