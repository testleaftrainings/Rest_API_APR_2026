package week3.day1;

import io.restassured.RestAssured;

public class PassPathVariableInPathRequest {
	
	public static void main(String[] args) {
		RestAssured.given()
        .auth()
        .basic("admin", "7d3iJH=K$jYf")        
        .pathParam("tableName", "incident")
        .when()
        .get("https://dev373619.service-now.com/api/now/table/{tableName}")
        .then()    
        .log().all() // Display request information in console
        .assertThat()
        .statusCode(200);
	}

}