package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void addplace() throws IOException
	{
		StepDefination sd=new StepDefination();
		if(StepDefination.placeid==null)
		{
			System.out.println("inside hook");
		sd.add_place_payload("SK Gupta", "English", "Pune MH");
		sd.user_calls_add_place_api_with_post_http_request("addPlaceAPI", "POST");
		sd.verify_place_id("SK Gupta", "getPlaceAPI");
		}
	}
}
