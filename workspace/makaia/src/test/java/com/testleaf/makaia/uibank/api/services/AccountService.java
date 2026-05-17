package com.testleaf.makaia.uibank.api.services;

import org.hamcrest.Matchers;
import org.testng.Assert;

import com.testleaf.makaia.general.utils.TestUtils;
import com.testleaf.makaia.uibank.deserialization.pojos.AccountDetails;

import io.restassured.http.ContentType;

public class AccountService extends UiBankServices {
	
	private final static String ENDPOINT = "/accounts";
	
	public AccountService fliterAccountDetailsByNickName(String nickName) {
		response = get(getPrecondtions()
			           .addQueryParam("filter[where][friendlyName]", nickName)
			           .addHeader("Authorization", "Bearer "+TestUtils.getTestData("token")), ENDPOINT);
		return this;
	}
	
	public AccountService validateSuccessResponse() {
		response.then()
		        .assertThat()
		        .statusCode(200)
		        .statusLine(Matchers.containsString("OK"))
		        .contentType(ContentType.JSON);
		return this;
	}
	
	public AccountService validateAccountNumber(String expected) {
		AccountDetails[] accountDetails = response.then()
		        .extract()
		        .as(AccountDetails[].class);
		Assert.assertEquals(accountDetails[0].getAccountNumber() , expected);
		return this;
	}
	
	public AccountService validateFriendlyName(String expected) {
		AccountDetails[] accountDetails = response.then()
		        .extract()
		        .as(AccountDetails[].class);
		Assert.assertEquals(accountDetails[0].getFriendlyName(), expected);
		return this;
	}
	
	public AccountService validateAccontType(String expected) {
		AccountDetails[] accountDetails = response.then()
		        .extract()
		        .as(AccountDetails[].class);
		Assert.assertEquals(accountDetails[0].getType(), expected);
		return this;
	}

}