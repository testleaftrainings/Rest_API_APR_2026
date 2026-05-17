package com.testleaf.matschie.servicenow.spec.builders;

import com.testleaf.matschie.constant.utils.Status;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilders {	
	
	public static ResponseSpecification successJsonResponse() {		
		return getResponseSpecBuilder()
				.expectStatusCode(Status.OK.getCode())
				.expectStatusLine(Status.OK.getMessage())
				.expectContentType(ContentType.JSON)
				.build();
	}
	
	public static ResponseSpecification successXmlResponse() {	
		return getResponseSpecBuilder()
				.expectStatusCode(Status.OK.getCode())
				.expectStatusLine(Status.OK.getMessage())
				.expectContentType(ContentType.XML)
				.build();
	}
	
	public static ResponseSpecification successNoContentResponse() {		
		return getResponseSpecBuilder()
				.expectStatusCode(Status.NO_CONTENT.getCode())
				.expectStatusLine(Status.NO_CONTENT.getMessage())
				.build();
	}
	
	public static ResponseSpecification successCreateResponse() {		
		return getResponseSpecBuilder()
				.expectStatusCode(Status.CREATED.getCode())
				.expectStatusLine(Status.CREATED.getMessage())
				.expectContentType(ContentType.JSON)
				.build();
	}
	
	public static ResponseSpecification notFoundErrorResponse() {		
		return getResponseSpecBuilder()
				.expectStatusCode(Status.NOT_FOUND.getCode())
				.expectStatusLine(Status.NOT_FOUND.getMessage())
				.expectContentType(ContentType.JSON)
				.build();
	}
	
	public static ResponseSpecBuilder getResponseSpecBuilder() {
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		return responseSpecBuilder;
	}

}