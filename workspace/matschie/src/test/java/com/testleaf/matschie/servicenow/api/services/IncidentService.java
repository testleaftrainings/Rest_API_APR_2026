package com.testleaf.matschie.servicenow.api.services;

import org.hamcrest.Matchers;

import com.testleaf.matschie.constant.utils.Status;
import com.testleaf.matschie.general.utils.TestUtlis;
import com.testleaf.matschie.rest.assured.api.client.RestAssuredApiClient;
import com.testleaf.matschie.servicenow.spec.builders.RequestSpecBuilders;

import io.restassured.http.ContentType;

public class IncidentService extends RestAssuredApiClient {	
	
	private final static String tableName = "incident"; 
	
	public IncidentService getAllRecords() {
		TestUtlis
		.setResponse(get(RequestSpecBuilders.getTableApiRequestSpec(tableName), "/{tableName}"));
		return this;
	}
	
	public IncidentService getARecord(String sysId) {
		TestUtlis
		.setResponse(get(RequestSpecBuilders.getTableApiRequestSpec(tableName)
				                            .pathParam("sys_id", sysId)
				                           , "/{tableName}/{sys_id}"));
		return this;		
	}
	
	public IncidentService getRecordsBasedOnCategory(String category) {
		TestUtlis
		.setResponse(get(RequestSpecBuilders.getTableApiRequestSpec(tableName)
				               .queryParam("category", category)
				                , "/{tableName}"));
		return this;
	}
	
	public IncidentService validateSuccessResponse() {
		TestUtlis.getResponse()
		         .then()
		         .assertThat()
		         .statusCode(Status.OK.getCode())
		         .statusLine(Status.OK.getMessage())
		         .contentType(ContentType.JSON);
		return this;
	}
	
	public IncidentService validateSysIdValueFromResponse(String expectedValue) {
		TestUtlis.getResponse()
		         .then()
		         .assertThat()
		         .body("result.sys_id", Matchers.equalTo(expectedValue));
		return this;
	}
	
	public IncidentService validateCategoryValueFromResponse(String expectedValue) {
		TestUtlis.getResponse()
		         .then()
		         .assertThat()
		         .body("result.category", Matchers.everyItem(Matchers.equalToIgnoringCase(expectedValue)));
		return this;
	}

}