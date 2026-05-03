package week3.day2;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DataDrivenApproach {
	
	@DataProvider
	public String[][] getData() {
		return new String[][] {
			{"RESTAPIJAN2026", "Create new record in Jan 2026", "hardware"},
			{"RESTAPIFEB2026", "Create new record in Feb 2026", "software"},
			{"RESTAPIMAR2026", "Create new record in Mar 2026", "database"}
		};
	}
	
	@Test(dataProvider = "getData")
	public void createNewRecordByPojo(String shortDescription, String description, String category) {
		
		CreateIncident createIncident = new CreateIncident();
		
		createIncident.setShort_description(shortDescription);
		createIncident.setDescription(description);
		createIncident.setCategory(category);
		
		RestAssured.given()
		           .baseUri("https://dev373619.service-now.com")
		           .basePath("/api/now/table")
		           .auth()
		           .basic("admin", "7d3iJH=K$jYf")
		           .contentType(ContentType.JSON)
		           .pathParam("tableName", "incident")
		           .log().all()
		           .when()
		           .body(createIncident)
		           .post("/{tableName}")
		           .then()
		           .statusCode(201)
		           // HTTP/1.1 201 Created
		           .statusLine(Matchers.containsString("Created"))
		           .contentType(ContentType.JSON)
		           .body("result.short_description", Matchers.equalTo(shortDescription))
		           .body("result.description", Matchers.equalTo(description))
		           .body("result.category", Matchers.equalTo(category));
		
	}

}