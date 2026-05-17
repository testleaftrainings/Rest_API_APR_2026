package com.testleaf.matschie.restful.booker.spec.builders;

import com.testleaf.matschie.constant.utils.Status;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilders {
	
	public static ResponseSpecification okResponseSpec() {
		return new ResponseSpecBuilder()
				   .expectStatusCode(Status.OK.getCode())
				   .expectStatusLine(Status.OK.getMessage())
				   .expectContentType(ContentType.JSON)
				   .build();
	}
	
	public static ResponseSpecification forbiddenResponseSpec() {
		return new ResponseSpecBuilder()
				   .expectStatusCode(Status.FORBIDDEN.getCode())
				   .expectStatusLine(Status.FORBIDDEN.getMessage())
				   .expectContentType(ContentType.JSON)
				   .build();
	}

}