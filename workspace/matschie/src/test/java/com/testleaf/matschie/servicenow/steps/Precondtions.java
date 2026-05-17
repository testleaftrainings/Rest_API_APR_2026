package com.testleaf.matschie.servicenow.steps;

import com.testleaf.matschie.general.utils.TestUtlis;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class Precondtions {
	
	RequestSpecification requestSpecification = RestAssured.given();

	@Given("set baseuri as {string}")
	public void set_baseuri_as(String baseUri) {
		TestUtlis.setTestData("requestSpec", requestSpecification.baseUri(baseUri));
	}

	@Given("set basepath as {string}")
	public void set_basepath_as(String basePath) {
		TestUtlis.setTestData("requestSpec", requestSpecification.basePath(basePath));
	}

	@Given("basic auth username as {string} and password as {string}")
	public void basic_auth_username_as_and_password_as(String username, String password) {
		TestUtlis.setTestData("requestSpec", requestSpecification.auth().basic(username, password));
	}

	@Given("path param variable name as {string} and value as {string}")
	public void path_param_variable_name_as_and_value_as(String variableName, String variableValue) {
		TestUtlis.setTestData("requestSpec", requestSpecification.pathParam(variableName, variableValue));
	}

}