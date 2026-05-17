package com.testleaf.matschie.restful.booker.api.services;

import com.testleaf.matschie.apiclient.design.ApiClient;
import com.testleaf.matschie.rest.assured.api.client.RestAssuredApiClient;

import io.restassured.response.Response;

public class RestfulBookerApi {
	
	protected ApiClient apiClient = new RestAssuredApiClient();
	protected Response response;
	
}