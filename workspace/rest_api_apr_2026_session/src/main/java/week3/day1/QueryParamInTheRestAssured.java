package week3.day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class QueryParamInTheRestAssured {
	
	@Test
	public void queryParameterToLimitTheResponse() {
		given()
		 .auth()
		 .basic("admin", "7d3iJH=K$jYf")
		 .queryParam("sysparm_limit", "5")
		 .pathParam("tableName", "incident")
	    .when()
		  .get("https://dev373619.service-now.com/api/now/table/{tableName}")		           
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(200);		           
	}
	
	@Test
	public void queryParameterToLimitField() {
		given()
        .auth()
        .basic("admin", "7d3iJH=K$jYf")
        .queryParam("sysparm_limit", "5")
        .queryParam("sysparm_fields", "sys_id,number,short_description,description,category")
        .pathParam("tableName", "incident")
        .when()
        .get("https://dev373619.service-now.com/api/now/table/{tableName}")		           
        .then()
        .log().all()
        .assertThat()
        .statusCode(200);	
	}
	
	@Test
	public void queryParameterToFilterHardwareCategory() {
		given()
        .auth()
        .basic("admin", "7d3iJH=K$jYf")
        .queryParam("sysparm_limit", "5")
        .queryParam("sysparm_fields", "sys_id,number,short_description,description,category")
        .queryParam("category", "hardware")
        .pathParam("tableName", "incident")
        .when()
        .get("https://dev373619.service-now.com/api/now/table/{tableName}")		           
        .then()
        .log().all()
        .assertThat()
        .statusCode(200);	
	}

}