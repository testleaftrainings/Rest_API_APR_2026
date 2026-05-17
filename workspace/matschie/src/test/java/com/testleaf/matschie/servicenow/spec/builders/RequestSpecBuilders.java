package com.testleaf.matschie.servicenow.spec.builders;

import static com.testleaf.matschie.general.utils.PropertiesUtlis.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilders {	
	
	public static RequestSpecification getTableApiRequestSpec(String tableName) {
		return getRequestSpecBuilder()
				.setBaseUri(getBaseUri())
				.setBasePath(getBasePath())
				.setAuth(RestAssured.basic(getUsername(), getPassword()))
				.addPathParam("tableName", tableName)
				.build();
	}
	
	public static RequestSpecification getRequestSpec() {
		return getRequestSpecBuilder().build();
	}
	
	private static RequestSpecBuilder getRequestSpecBuilder() {
		return new RequestSpecBuilder();
	}

}