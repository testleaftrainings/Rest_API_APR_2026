package com.testleaf.matschie.apiclient.design;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface ApiClient {
	
	public Response get(RequestSpecification requestSpecBuilder, String endPoint);

	public Response post(RequestSpecification requestSpecBuilder, String endPoint, Object requestPayload);

	public Response put(RequestSpecification requestSpecBuilder, String endPoint, Object requestPayload);

	public Response patch(RequestSpecification requestSpecBuilder, String endPoint, Object requestPayload);

	public Response delete(RequestSpecification requestSpecBuilder, String endPoint);

}