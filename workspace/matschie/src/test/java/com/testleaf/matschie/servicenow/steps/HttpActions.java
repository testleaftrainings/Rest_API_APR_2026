package com.testleaf.matschie.servicenow.steps;

import com.testleaf.matschie.apiclient.design.ApiClient;
import com.testleaf.matschie.general.utils.TestUtlis;
import com.testleaf.matschie.rest.assured.api.client.RestAssuredApiClient;

import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class HttpActions {
	
	ApiClient apiClient = new RestAssuredApiClient();
	
	@When("hit get method with url {string}")
	public void hit_get_method_with_url(String endpoint) {
	    Response response = apiClient.get(TestUtlis.getRequestSpecification(), endpoint);
	    TestUtlis.setResponse(response);
	}

}