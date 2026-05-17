package com.testleaf.matschie.restful.booker.spec.builders;

import com.testleaf.matschie.general.utils.PropertiesUtlis;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilders {
	
	public static RequestSpecification getBookingServiceSpec() {
		return new RequestSpecBuilder()
				   .setBaseUri(PropertiesUtlis.getBaseUri())					   
				   .build();
	}

}