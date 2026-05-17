package com.testleaf.matschie.rest.assured.api.client;

import java.io.File;

import com.testleaf.matschie.apiclient.design.ApiClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredApiClient implements ApiClient {
	
	private RequestSpecification given(RequestSpecification requestSpecification) {
		return RestAssured.given()
				          .spec(requestSpecification)
				          .filters(new RequestLoggingFilter(), 
				        		       new ErrorResponseLoggingFilter(),
				        		       new AllureRestAssured());
	}	

	@Override
	public Response get(RequestSpecification requestSpecification, String endPoint) {		
		return given(requestSpecification).get(endPoint);
	}

	@Override
	public Response post(RequestSpecification requestSpecification, String endPoint, Object requestPayload) {
		if(requestPayload instanceof String) {
			 return given(requestSpecification).body((String) requestPayload).post(endPoint);
		 } else if(requestPayload instanceof File) {
			 return given(requestSpecification).body((File) requestPayload).post(endPoint);
		 } else if(requestPayload == null) {
			 return given(requestSpecification).post(endPoint);
		 } else {
			 return given(requestSpecification).body(requestPayload).post(endPoint);
		 }
	}

	@Override
	public Response put(RequestSpecification requestSpecification, String endPoint, Object requestPayload) {
		if(requestPayload instanceof String) {
			 return given(requestSpecification).body((String) requestPayload).put(endPoint);
		 } else if(requestPayload instanceof File) {
			 return given(requestSpecification).body((File) requestPayload).put(endPoint);
		 } else if(requestPayload == null) {
			 return given(requestSpecification).put(endPoint);
		 } else {
			 return given(requestSpecification).body(requestPayload).put(endPoint);
		 }
	}

	@Override
	public Response patch(RequestSpecification requestSpecification, String endPoint, Object requestPayload) {
		if(requestPayload instanceof String) {
			 return given(requestSpecification).body((String) requestPayload).patch(endPoint);
		 } else if(requestPayload instanceof File) {
			 return given(requestSpecification).body((File) requestPayload).patch(endPoint);
		 } else if(requestPayload == null) {
			 return given(requestSpecification).patch(endPoint);
		 } else {
			 return given(requestSpecification).body(requestPayload).patch(endPoint);
		 }
	}

	@Override
	public Response delete(RequestSpecification requestSpecification, String endPoint) {
		return given(requestSpecification).delete(endPoint);
	}

}