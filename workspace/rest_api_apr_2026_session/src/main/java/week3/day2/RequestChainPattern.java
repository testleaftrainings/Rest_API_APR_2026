package week3.day2;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import week3.day2.pojos.UpdateRecord;

import static io.restassured.RestAssured.*;

public class RequestChainPattern {
	
	String sysId;
	
	@Test(priority = 1)
	public void createNewRecord() {
		sysId = given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")
		 .pathParam("tableName", "incident")
		 .contentType(ContentType.JSON)
		 .log().all()
		.when()
		 .post("/{tableName}")
		.then()
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
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")
		 .pathParam("tableName", "incident")
		 .pathParam("sys_id", sysId)
		.when()
		 .get("/{tableName}/{sys_id}")
		.then()
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
		
		UpdateRecord updateRecord = new UpdateRecord();
		updateRecord.setShortDescription("RESTAPIAPR2026");
		updateRecord.setDescription("Update existing record using patch method");
		updateRecord.setCategory("hardware");
		
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")
		 .pathParam("tableName", "incident")
		 .pathParam("sys_id", sysId)
		 .contentType(ContentType.JSON)
		.when()
		 .body(updateRecord)
		 .patch("/{tableName}/{sys_id}")
		.then()
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
	public void deleteExistingRecord() {
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")
		 .pathParam("tableName", "incident")
		 .pathParam("sys_id", sysId)
		.when()
		 .delete("/{tableName}/{sys_id}")
		.then()
		 .assertThat()
		 .statusCode(204)
		 .statusLine(Matchers.containsString("No Content"));
	}

}