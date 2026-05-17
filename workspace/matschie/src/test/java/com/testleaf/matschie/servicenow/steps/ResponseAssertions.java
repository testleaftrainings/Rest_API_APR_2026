package com.testleaf.matschie.servicenow.steps;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

import com.testleaf.matschie.general.utils.TestUtlis;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

public class ResponseAssertions {
	
	@Then("response validation")
	public void response_validation(DataTable dataTable) {
	    List<Map<String, String>> maps = dataTable.asMaps();
	    for (Map<String, String> map : maps) {
			TestUtlis.getResponse().then().statusCode(Integer.parseInt(map.get("statusCode")));
			TestUtlis.getResponse().then().statusLine(Matchers.containsStringIgnoringCase(map.get("statusMessage")));
			TestUtlis.getResponse().then().contentType(Matchers.containsStringIgnoringCase(map.get("responseFormate")));
		}
	}

}