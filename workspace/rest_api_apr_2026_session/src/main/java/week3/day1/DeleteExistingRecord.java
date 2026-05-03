package week3.day1;

import static io.restassured.RestAssured.*;

import io.restassured.filter.log.LogDetail;

public class DeleteExistingRecord {

	public static void main(String[] args) {
		given()
		 .baseUri("https://dev373619.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")
		 .pathParam("tableName", "incident")
		 .pathParam("sys_id", "19bc61e293e047109ff3fc20ed03d6f4")
		 .log().all()
		.when()
		 .delete("/{tableName}/{sys_id}")
		.then()
		 .log().ifValidationFails(LogDetail.ALL)
		 .assertThat()
		 .statusCode(204);
	}

}
