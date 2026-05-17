package week4.day2;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static week4.day2.ConfigManager.getConfigProperties;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import week3.day2.pojos.UpdateRecord;

public class ServiceNowE2eTest {	
	
	TestUtils testUtils = new TestUtils();
	
	@BeforeMethod
	public void arrange() {
		 testUtils.setRequestSpecification(given()
		  .filter(new AllureRestAssured())
	      .baseUri(getConfigProperties().getUrl())
		  .basePath(getConfigProperties().getPath())
		  .auth()
		  .basic(getConfigProperties().getUserName(), getConfigProperties().getPassword())
		  .pathParam("tableName", getConfigProperties().getTableName())
		  .log().all());
	}
	
	@Test(priority = 1)
	public void createANewRecord() {
		// Act
		testUtils.setResponse(testUtils.getRequestSpecification()
				            .contentType(ContentType.JSON)
				            .post("/{tableName}"));
		
		// Assert
		String sysId = testUtils.getResponse().then()
		 .log().all()
		 .assertThat()
		 .statusCode(201)
		 .statusLine(Matchers.containsString("Created"))
		 .contentType(ContentType.JSON)
		 .body("result", Matchers.hasKey("sys_id"))
		 .body("result.sys_id", Matchers.not(Matchers.emptyOrNullString()))
		 .extract()
		 .jsonPath()
		 .getString("result.sys_id");
		
		testUtils.setTestData(sysId);
	}
	
	@Test(priority = 2)
	public void getARecord() {	
		
		testUtils.setResponse(testUtils.getRequestSpecification()
				              .pathParam("sys_id", testUtils.getTestData())
				              .get("/{tableName}/{sys_id}"));
		
		testUtils.getResponse().then()
		 .assertThat()
		 .statusCode(200)
		 .statusLine(Matchers.containsString("OK"))
		 .contentType(ContentType.JSON)
		 .body("result.sys_id", Matchers.equalTo(testUtils.getTestData()))	
		 .body("result.short_description", Matchers.emptyString())
		 .body("result.description", Matchers.emptyString())
		 .body("result.category", Matchers.equalTo("inquiry"));
	}
	
	@Test(priority = 3)
	public void updateExistingRecord() {
		
		// Arrange
		UpdateRecord updateRecord = new UpdateRecord();
		updateRecord.setShortDescription("RESTAPIAPR2026");
		updateRecord.setDescription("Update existing record using patch method");
		updateRecord.setCategory("hardware");		
		
		testUtils.setResponse(testUtils.getRequestSpecification()
				              .contentType(ContentType.JSON)
				              .pathParam("sys_id", testUtils.getTestData())
				              .body(updateRecord)
				              .patch("/{tableName}/{sys_id}"));
		
		testUtils.getResponse().then()
		 .log().all()
		 .assertThat()
		 .statusCode(200)
		 .statusLine(Matchers.containsString("OK"))
		 .contentType(ContentType.JSON)
		 .body("result.sys_id", Matchers.equalTo(testUtils.getTestData()))	
		 .body("result.short_description", Matchers.equalTo(updateRecord.getShortDescription()))
		 .body("result.description", Matchers.equalTo(updateRecord.getDescription()))
		 .body("result.category", Matchers.equalTo(updateRecord.getCategory()));
		
	}
	
	@Test(priority = 4)
	public void deleteExisintRecord() {		
		
		testUtils.setResponse(testUtils.getRequestSpecification()
				              .pathParam("sys_id", testUtils.getTestData())
				              .delete("/{tableName}/{sys_id}"));
		
		testUtils.getResponse().then()
		 .assertThat()
		 .statusCode(204)
		 .statusLine(Matchers.containsString("No Content"));
	}

}