package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{

	RequestSpecification res;
	static Response response;
	public static String placeid;
	TestDataBuild tdb=new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		res = given().spec(RequestSpec()).body(tdb.AppPlace(name, language, address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_add_place_api_with_post_http_request(String resource, String method) {
		APIResource api=APIResource.valueOf(resource);
		if(method.equals("POST"))
		{
	    response = res.when().post(api.getresource()).then().spec(responsespec()).extract().response();
		System.out.println("Response output is:-"+response.getBody().asString());
		}
		else if(method.equals("GET"))
		{
			response = res.when().get(api.getresource()).then().spec(responsespec()).extract().response();
		}
		
	}
	
	@Then("the api call got success with status code {int}")
	public void then_the_api_call_got_success_with_status_code(int int1) {
	   assertEquals(response.getStatusCode(),int1); 
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String Key, String Value) {
	   //String respon=response.asString();
	   //JsonPath js=new JsonPath(respon);
	   assertEquals(getjsonvalue(Key,response),Value);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id(String name, String resource) throws IOException
	{
		placeid=getjsonvalue("place_id",response);
		res=given().spec(RequestSpec()).queryParam("place_id", placeid);
		user_calls_add_place_api_with_post_http_request(resource, "GET");
		assertEquals(getjsonvalue("name",response),name);
	}
	
	@Given("fetch the {string}")
	public void fetch_the(String string) throws IOException {
	    System.out.println(placeid);
	    res = given().spec(RequestSpec()).body(tdb.deletepayload(placeid));   
	}
}

