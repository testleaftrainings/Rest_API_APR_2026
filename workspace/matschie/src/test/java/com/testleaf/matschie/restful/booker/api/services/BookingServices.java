package com.testleaf.matschie.restful.booker.api.services;

import static com.testleaf.matschie.restful.booker.spec.builders.RequestSpecBuilders.*;
import static com.testleaf.matschie.restful.booker.spec.builders.ResponseSpecBuilders.*;

import org.hamcrest.Matchers;

import com.testleaf.matschie.general.utils.PropertiesUtlis;
import com.testleaf.matschie.general.utils.TestUtlis;
import com.testleaf.matschie.restful.booker.deserialization.pojos.BookingResponse;
import com.testleaf.matschie.restful.booker.serialization.pojos.CreateBooking;
import com.testleaf.matschie.restful.booker.serialization.pojos.UpdateBooking;

import io.restassured.http.ContentType;

public class BookingServices extends RestfulBookerApi {
	
	private final static String ENDPOINT = "booking";
	
	public BookingServices createNewBooking(CreateBooking createBooking) {
		response = apiClient.post(getBookingServiceSpec()
				                  .contentType(ContentType.JSON), ENDPOINT, createBooking);
		TestUtlis.setResponse(response);
		return this;
	}
	
	public BookingServices getBookingInfoById(String bookingId) {
		response = apiClient.get(getBookingServiceSpec()
				                 .pathParam("bookingID", bookingId), ENDPOINT+"/{bookingID}");
		TestUtlis.setResponse(response);
		return this;
	}
	
	public BookingServices updateBookingInfoById(String bookingId, UpdateBooking updateBooking) {
		response = apiClient.put(getBookingServiceSpec()
				                 .pathParam("bookingID", bookingId)
				                 .contentType(ContentType.JSON)
				                 .auth()
				                 .preemptive()
				                 .basic(PropertiesUtlis.getUsername(), PropertiesUtlis.getPassword()), 
				                 ENDPOINT+"/{bookingID}", updateBooking);
		TestUtlis.setResponse(response);
		return this;
	}
	
	public BookingServices validateSuccessfulBooking() {
		TestUtlis.getResponse()
		         .then()
		         .spec(okResponseSpec());
		return this;
	}
	
	public BookingServices extractBookingId() {
		int bookingid = TestUtlis.getResponse()
		         .then()
		         .extract()
		         .as(BookingResponse.class)
		         .getBookingid();
		TestUtlis.setTestData("bookingid", String.valueOf(bookingid));
		return this;
	}
	
	public BookingServices validateBookingFirstName(String expected) {
		TestUtlis.getResponse()
		         .then()
		         .assertThat()
		         .body("firstname", Matchers.equalTo(expected));
		return this;
	}

}