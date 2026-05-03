package week3.day1;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class SendPostRequestWithARequestBody {
	
	@Test
	public void createNewRecordRequestBodyAsString() {
		
		String requestBody = """
				{
                  "short_description": "RESTAPIMAR2026"
                }
				""";
				
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .pathParam("tableName", "incident")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")		 
		 .contentType(ContentType.JSON)
		 .log().all()
		.when()
		 .body(requestBody)
		 .post("/{tableName}")
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(201);
	}
	
	@Test
	public void createNewRecordRequestBodyAsFile() {
		
		File requestBody = new File("src/main/resources/request_body/Create-record.json");
				
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .pathParam("tableName", "incident")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")		
		 .contentType(ContentType.JSON)
		 .log().all()
		.when()
		 .body(requestBody)
		 .post("/{tableName}")
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(201);
	}

}