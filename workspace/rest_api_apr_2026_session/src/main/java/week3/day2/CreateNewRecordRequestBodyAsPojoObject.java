package week3.day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import week3.day2.pojos.CreateRecord;

public class CreateNewRecordRequestBodyAsPojoObject {	
	
	@Test
	public void createNewRecordByPojo() {
		
		CreateRecord createRecord = new CreateRecord();
		
		createRecord.setShortDescription("RESTAPIAPR2026");
		createRecord.setDescription("Create new record using the POST method");
		createRecord.setCategory("database");
		
		RestAssured.given()
		           .baseUri("https://dev373619.service-now.com")
		           .basePath("/api/now/table")
		           .auth()
		           .basic("admin", "7d3iJH=K$jYf")
		           .contentType(ContentType.JSON)
		           .pathParam("tableName", "incident")
		           .log().all()
		           .when()
		           .body(createRecord)
		           .post("/{tableName}")
		           .then()
		           .log().all()
		           .statusCode(201)
		           .contentType(ContentType.JSON);
		
	}

}