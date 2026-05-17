package com.testleaf.makaia.servicenow.api.som;

import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;

import com.testleaf.makaia.constant.utls.Status;
import com.testleaf.makaia.servicenow.deserialization.pojos.Result;
import com.testleaf.makaia.servicenow.deserialization.pojos.TableApiJson;
import com.testleaf.makaia.servicenow.deserialization.pojos.TableApiJsonArray;
import com.testleaf.makaia.servicenow.serialization.pojos.CreateIncident;
import com.testleaf.makaia.servicenow.serialization.pojos.UpdateIncident;

import io.restassured.http.ContentType;

public class IncidentSerivce extends ServiceNowTableAPI {

	private static final String TABLE_NAME = "incident";

	public IncidentSerivce() {
		requestBuilder = globalRequest(TABLE_NAME);
	}
	
	public IncidentSerivce fetchIncidentRecords() {
		response = apiClient.get(requestBuilder, "/{tableName}");
		return this;
	}
	
	public IncidentSerivce fetchIncidentRecord(String sysId) {
		response = apiClient.get(requestBuilder.addPathParam("sys_id", sysId), "/{tableName}/{sys_id}");
		return this;
	}
	
	public IncidentSerivce fetchIncidentRecordByNumber(String number) {
		response = apiClient.get(requestBuilder.addQueryParam("sysparm_query", "number="+number), "/{tableName}");
		return this;
	}
	
	public IncidentSerivce createIncidentRecord() {
		response = apiClient.post(requestBuilder.setContentType(ContentType.JSON), "/{tableName}", null);
		return this;
	}
	
	public IncidentSerivce createIncidentRecord(CreateIncident payload) {
		response = apiClient.post(requestBuilder, "/{tableName}", payload);
		return this;
	}
	
	public IncidentSerivce updateIncidentRecord(UpdateIncident payload, String sysId) {
		response = apiClient.put(requestBuilder.setContentType(ContentType.JSON)
				                               .addPathParam("sys_id", sysId),
				                                "/{tableName}/{sys_id}", payload);
		return this;
	}
	
	public IncidentSerivce fetchOnlyHardwareCategoryIncidentRecords() {		
		response = apiClient.get(requestBuilder.addQueryParam("sysparm_query", "category=hardware"), "/{tableName}");
		return this;
	}
	
	public IncidentSerivce deleteIncidentRecord(String sysId) {
		response = apiClient.delete(requestBuilder.addPathParam("sys_id", sysId), "/{tableName}/{sys_id}");
		return this;
	}
	
	public IncidentSerivce validateSuccessResponse() {
		validateStatusCode(Status.OK.getCode());
		validateStatusLine(Status.OK.getMessage());
		validateResponseFormat("JSON");
		return this;
	}
	
	public IncidentSerivce validateCreationResponse() {
		validateStatusCode(Status.CREATED.getCode());
		validateStatusLine(Status.CREATED.getMessage());
		validateResponseFormat("JSON");
		return this;
	}
	
	public IncidentSerivce validateDeletionResponse() {
		validateStatusCode(Status.NO_CONTENT.getCode());
		validateStatusLine(Status.NO_CONTENT.getMessage());
		return this;
	}
	
	public IncidentSerivce validateNotFoundResponse() {
		validateStatusCode(Status.NOT_FOUND.getCode());
		validateStatusLine(Status.NOT_FOUND.getMessage());
		validateResponseFormat("JSON");
		return this;
	}
	
	public IncidentSerivce validateCategories(String expected) {
		TableApiJsonArray deSerializeResponse = deSerializeResponse(response.prettyPrint(), TableApiJsonArray.class);
		List<Result> results = deSerializeResponse.getResultJsonArray();
		for (Result result : results) {
			assertThat(result.getCategory(), equalToIgnoringCase(expected));
		}
		return this;
	}
	
	public String extractIncidentNumber() {
		String number = deSerializeResponse(response.prettyPrint(), TableApiJson.class).getResult().getNumber();
		assertThat(number, not(emptyOrNullString()));
		return number;
	}
	
	public IncidentSerivce validateSysId(String expected) {
		String sys_id = deSerializeResponse(response.prettyPrint(), TableApiJson.class).getResult().getSysId();
		assertThat(sys_id, not(emptyOrNullString()));
		assertThat(sys_id, equalTo(expected));
		return this;
	}
	
	public IncidentSerivce validateIncidentNumber(String expected) {
		assertThat(deSerializeResponse(response.prettyPrint(), TableApiJsonArray.class).getResultJsonArray().getFirst().getNumber(), not(emptyOrNullString()));
		assertThat(deSerializeResponse(response.prettyPrint(), TableApiJsonArray.class).getResultJsonArray().getFirst().getNumber(), equalTo(expected));
		return this;
	}
	
	private void validateStatusCode(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}
	
	private void validateStatusLine(String statusLine) {
		response.then().assertThat().statusLine(statusLine);
	}
	
	private void validateResponseFormat(String responseFormat) {
		if (responseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if (responseFormat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		} else {
			throw new RuntimeException("Currently matschie framework support JSON or XML.");
		}
	}

}