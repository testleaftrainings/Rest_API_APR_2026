package week3.day1;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class SendPostRequestWithoutRequestBody {
	
	@Test
	public void createNewRecord() {
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .pathParam("tableName", "incident")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")
		 //.header("Content-Type", "application/json")
		 .contentType(ContentType.JSON)
		.when()
		 .post("/{tableName}")
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(201);	  
	}

}