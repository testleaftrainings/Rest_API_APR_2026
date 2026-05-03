package week3.day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class MyFirstRestAssuredScript {
	
	@Test
	public void testGetMethod() {
		RestAssured.given()
		           .auth()
		           .basic("admin", "7d3iJH=K$jYf")
		           .log().all() // Display request information in console
		           .when()
		           .get("https://dev373619.service-now.com/api/now/table/incident")
		           .then()
		           .log().all() // Display response information in console
		           .assertThat()
		           .statusCode(200);
	}

}