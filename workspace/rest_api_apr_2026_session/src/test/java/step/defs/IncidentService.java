package step.defs;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.CreateIncident;

public class IncidentService {
	
	private RequestSpecification requestSpecification = RestAssured.given()
			                                                       .filter(new RequestLoggingFilter())
			                                                       .filter(new ResponseLoggingFilter());
	private Response response;
	private CreateIncident createIncident = new CreateIncident();
	

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
	
	@Given("set value of the shortDescription key as {string}")
	public void set_value_of_the_short_description_key_as(String shortDescription) {
	    createIncident.setShort_description(shortDescription);
	}
	
	@Given("set value of the description key as {string}")
	public void set_value_of_the_description_key_as(String description) {
	   createIncident.setDescription(description);
	}
	
	@When("hit post metho with url {string}")
	public void hit_post_metho_with_url(String endPoint) {
	    response = requestSpecification.body(createIncident).post(endPoint);
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
	
	@Then("response validation")
	public void response_validation(DataTable dataTable) {
	    List<Map<String, String>> rows = dataTable.asMaps();
	    for (Map<String, String> row : rows) {
			response.then().assertThat().statusCode(Integer.parseInt(row.get("statusCode")));
			response.then().assertThat().statusLine(Matchers.containsString(row.get("statusMessage")));
			response.then().assertThat().contentType(Matchers.containsString(row.get("responseFormate").toLowerCase()));
		}
	}

}