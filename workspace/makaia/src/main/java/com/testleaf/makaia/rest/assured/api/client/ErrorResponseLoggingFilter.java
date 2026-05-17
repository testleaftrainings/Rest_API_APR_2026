package com.testleaf.makaia.rest.assured.api.client;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ErrorResponseLoggingFilter implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		Response response = ctx.next(requestSpec, responseSpec);
		if(response.getStatusCode() >= 400) {
			System.out.println("============ Request Log ============");
			System.err.println("Request URI: " + requestSpec.getURI());
            System.err.println("Request Body: " + requestSpec.getBody());
            System.err.println("Response Body: " + response.asString());
            System.out.println("=====================================");
		}
		return response;
	}

}
