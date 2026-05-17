package week4.day2;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtils {

	private RequestSpecification requestSpecification;
	private Response response;
	private String testData;

	public RequestSpecification getRequestSpecification() {
		return requestSpecification;
	}

	public void setRequestSpecification(RequestSpecification requestSpecification) {
		this.requestSpecification = requestSpecification;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getTestData() {
		return testData;
	}

	public void setTestData(String testData) {
		this.testData = testData;
	}

}