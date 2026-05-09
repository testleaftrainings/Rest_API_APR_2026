package step.defs;

import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class IncidentService {
	
	private RequestSpecification requestSpecification = RestAssured.given()
			                                                       .filter(new RequestLoggingFilter())
			                                                       .filter(new ResponseLoggingFilter());
	private Response response;

	@Given("set baseuri as {string}")
	public void set_baseuri_as(String baseUri) {
		requestSpecification.baseUri(baseUri);
	}

	@Given("set basepath as {string}")
	public void set_basepath_as(String basePath) {
		requestSpecification.basePath(basePath);
	}

	@Given("basic auth username as {string} and password as {string}")
	public void basic_auth_username_as_and_password_as(String username, String password) {
		requestSpecification.auth().basic(username, password);
	}

	@Given("path param variable name as {string} and value as {string}")
	public void path_param_variable_name_as_and_value_as(String variableName, String variableValue) {
		requestSpecification.pathParam(variableName, variableValue);
	}
	
	@Given("query param key as {string} and value as {string}")
	public void query_param_key_as_and_value_as(String key, String value) {
	    requestSpecification.queryParam(key, value);
	}
	
	@Given("header key as {string} and value as {string}")
	public void header_key_as_and_value_as(String key, String value) {
		requestSpecification.header(key, value);	    
	}

	@When("hit get method with url {string}")
	public void hit_get_method_with_url(String endPoint) {
		response = requestSpecification.get(endPoint);
	}

	@Then("status code is {int}")
	public void status_code_is(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@Then("status message is {string}")
	public void status_message_is(String statusMessage) {
		response.then().assertThat().statusLine(Matchers.containsString(statusMessage));
	}

	@Then("response content fromate is {string}")
	public void response_content_fromate_is(String responseFormate) {
		
		if(responseFormate.equals("JSON")) {
			response.then().contentType(ContentType.JSON);
		} else if(responseFormate.equals("XML")) {
			response.then().contentType(ContentType.XML);
		}
		
		//response.then().assertThat().contentType(Matchers.containsString(responseFormate.toLowerCase()));
	}

}