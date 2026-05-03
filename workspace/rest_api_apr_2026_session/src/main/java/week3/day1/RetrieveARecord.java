package week3.day1;

import static io.restassured.RestAssured.*;

import io.restassured.filter.log.LogDetail;

public class RetrieveARecord {

	public static void main(String[] args) {
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")
		 .pathParam("tableName", "incident")
		 .pathParam("sys_id", "1c832706732023002728660c4cf6a7b9")
		 .log().all()
		.when()
		 .get("/{tableName}/{sys_id}")
		.then()
		 .log().ifValidationFails(LogDetail.ALL)
		 .assertThat()
		 .statusCode(200);
	}

}
