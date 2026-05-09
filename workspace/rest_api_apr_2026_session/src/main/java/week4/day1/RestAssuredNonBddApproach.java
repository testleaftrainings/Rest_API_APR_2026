package week4.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredNonBddApproach {
	
	public static void main(String[] args) {
		
		// Arrange		
		RequestSpecification request = RestAssured.given()
				   .log().all()
		           .baseUri("https://dev373619.service-now.com")
		           .basePath("/api/now/table")
		           .auth()
		           .basic("admin", "7d3iJH=K$jYf")
		           .pathParam("tableName", "incident");
		           
		// Act
		Response response = request.pathParam("sys_id", "1c741bd70b2322007518478d83673af3")
				                   .get("/{tableName}/{sys_id}");
		
		// Assert
		response.then()
		        .log().all()
		        .statusCode(200)
		        .contentType(ContentType.JSON)
		        .body("result.sys_id", Matchers.equalTo("1c741bd70b2322007518478d83673af3"));
		
		
	}

}