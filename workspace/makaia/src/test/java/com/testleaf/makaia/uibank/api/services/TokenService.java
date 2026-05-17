package com.testleaf.makaia.uibank.api.services;

import org.hamcrest.Matchers;

import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.uibank.serialization.pojos.CreateToken;

import io.restassured.http.ContentType;

public class TokenService extends UiBankServices {
	
	private final static String ENDPOINT = "/users/login";
	
	public TokenService createToken(String username, String password) {
		CreateToken createToken = new CreateToken();
		createToken.setUsername(username);
		createToken.setPassword(password);
		response = post(getPrecondtions().setContentType(ContentType.JSON), ENDPOINT, createToken);	
		return this;
	}
	
	public TokenService validateCreateTokenResponse() {
		response.then()
		        .statusCode(200)
		        .statusLine(Matchers.containsString("OK"))
		        .contentType(ContentType.JSON);
		return this;
	}
	
	public TokenService extractToken() {
		String token = response.then()
		        .extract()
		        .jsonPath()
		        .getString("id");
		TestUtils.setTestData("token", token);
		return this;
	}

}