package com.testleaf.makaia.uibank.api.services;

import com.testleaf.makaia.general.utils.PropertiesHandler;
import com.testleaf.makaia.rest.assured.api.client.RestAssuredApiClient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class UiBankServices extends RestAssuredApiClient {
	
	protected Response response;
	
	protected RequestSpecBuilder getPrecondtions() {
		return new RequestSpecBuilder()
				   .setBaseUri(PropertiesHandler.config("uibank.backend.base.uri"))
				   .setBasePath(PropertiesHandler.config("uibank.backend.base.path"));
	}

}