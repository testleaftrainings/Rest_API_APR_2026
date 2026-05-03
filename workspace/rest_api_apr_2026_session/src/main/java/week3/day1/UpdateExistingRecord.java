package week3.day1;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.http.ContentType;

public class UpdateExistingRecord {
	
	public static void main(String[] args) {
		
		File requestBody = new File("src/main/resources/request_body/Update-record.json");
		
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .pathParam("tableName", "incident")
		 .pathParam("sysId", "8deb696293e047109ff3fc20ed03d626")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")		
		 .contentType(ContentType.JSON)
		 .log().all()
		.when()
		 .body(requestBody)
		 .put("/{tableName}/{sysId}")
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(200);
	}

}