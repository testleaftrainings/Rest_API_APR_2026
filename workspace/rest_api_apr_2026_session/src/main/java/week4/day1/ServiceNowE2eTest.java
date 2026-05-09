package week4.day1;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import week3.day2.pojos.UpdateRecord;

public class ServiceNowE2eTest {
	
	private RequestSpecification request;
	private Response response;
	private String sysId;
	
	// Common across all the test
	@BeforeMethod
	public void arrange() {
		request = given()
				  .filter(new AllureRestAssured())
				  .baseUri("https://dev373619.service-now.com")
				  .basePath("/api/now/table")
				  .auth()
				  .basic("admin", "7d3iJH=K$jYf")
				  .pathParam("tableName", "incident")
				  .log().all();
	}
	
	@Test(priority = 1)
	public void createANewRecord() {
		// Act
		response = request.contentType(ContentType.JSON)
				          .post("/{tableName}");
		
		// Assert
		sysId = response.then()
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
	}
	
	@Test(priority = 2)
	public void getARecord() {
		response = request.pathParam("sys_id", sysId)
				          .get("/{tableName}/{sys_id}");
		
		response.then()
		 .assertThat()
		 .statusCode(200)
		 .statusLine(Matchers.containsString("OK"))
		 .contentType(ContentType.JSON)
		 .body("result.sys_id", Matchers.equalTo(sysId))	
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
		
		response = request.contentType(ContentType.JSON)
				          .pathParam("sys_id", sysId)
				          .body(updateRecord)
				          .patch("/{tableName}/{sys_id}");
		
		response.then()
		 .log().all()
		 .assertThat()
		 .statusCode(200)
		 .statusLine(Matchers.containsString("OK"))
		 .contentType(ContentType.JSON)
		 .body("result.sys_id", Matchers.equalTo(sysId))	
		 .body("result.short_description", Matchers.equalTo(updateRecord.getShortDescription()))
		 .body("result.description", Matchers.equalTo(updateRecord.getDescription()))
		 .body("result.category", Matchers.equalTo(updateRecord.getCategory()));
		
	}
	
	@Test(priority = 4)
	public void deleteExisintRecord() {
		response = request.pathParam("sys_id", sysId)
		                  .delete("/{tableName}/{sys_id}");
		
		response.then()
		 .assertThat()
		 .statusCode(204)
		 .statusLine(Matchers.containsString("No Content"));
	}

}