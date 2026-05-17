package com.testleaf.matschie.servicenow.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testleaf.matschie.general.utils.TestUtlis;
import com.testleaf.matschie.servicenow.api.services.IncidentService;

public class ServicenowTest {
	
	@BeforeClass
	public void beforeClass() {
		TestUtlis.setTestData("sys_id", "1c741bd70b2322007518478d83673af3");
	}
	
	@Test
	public void userShouldAbleFetchAllRecords() {
		new IncidentService()
		    .getAllRecords()
		    .validateSuccessResponse();
	}
	
	@Test
	public void userShouldAbleFetchARecord() {
		new IncidentService()
		    .getARecord(TestUtlis.getTestData("sys_id"))
		    .validateSuccessResponse()
		    .validateSysIdValueFromResponse(TestUtlis.getTestData("sys_id"));
	}
	
	@Test
	public void userShouldAbleToFetchHardwareCatgoryRecords() {
		new IncidentService()
		    .getRecordsBasedOnCategory("hardware")
		    .validateSuccessResponse()
		    .validateCategoryValueFromResponse("hardware");
	}

}