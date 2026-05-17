package com.testleaf.matschie.restful.booker.api.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testleaf.matschie.general.utils.AllureHandler;
import com.testleaf.matschie.general.utils.TestUtlis;
import com.testleaf.matschie.restful.booker.api.services.BookingServices;
import com.testleaf.matschie.restful.booker.serialization.pojos.BookingDates;
import com.testleaf.matschie.restful.booker.serialization.pojos.CreateBooking;
import com.testleaf.matschie.restful.booker.serialization.pojos.UpdateBooking;

public class BookerApiTests {
	
	CreateBooking createBooking = new CreateBooking();
	UpdateBooking updateBooking = new UpdateBooking();
	BookingDates bookingDates = new BookingDates();
	
	@BeforeClass
	public void beforeClass() {
		createBooking.setFirstname("Mark");
		createBooking.setLastname("Brown");
		createBooking.setTotalprice(111);
		createBooking.setDepositpaid(true);
		bookingDates.setCheckin("2026-05-28");
		bookingDates.setCheckout("2026-05-31");
		createBooking.setBookingdates(bookingDates);
		createBooking.setAdditionalneeds("Breakfast");
		
		updateBooking.setFirstname("James");
		updateBooking.setLastname("Brown");
		updateBooking.setTotalprice(120);
		updateBooking.setDepositpaid(false);		
		updateBooking.setBookingdates(bookingDates);
		updateBooking.setAdditionalneeds("Breakfast,lunch");
	}
	
	@Test
	public void userShouldAbleToCreateBooking() {
		new BookingServices()
		    .createNewBooking(createBooking)
		    .validateSuccessfulBooking()
		    .extractBookingId();		
	}
	
	@Test
	public void userShouldAbleToFetchBookingInfoById() {
		new BookingServices()
		    .getBookingInfoById(TestUtlis.getTestData("bookingid"))
		    .validateSuccessfulBooking()
		    .validateBookingFirstName(createBooking.getFirstname());
	}
	
	@Test
	public void userShouldAbleToUpdateExisintingBookingInfo() {
		new BookingServices()
		    .updateBookingInfoById(TestUtlis.getTestData("bookingid"), updateBooking)
		    .validateSuccessfulBooking()
		    .validateBookingFirstName(updateBooking.getFirstname());
	}
	
	@AfterSuite
	public void afterSuite() {
		AllureHandler.moveHistoryFolderToAllureResults();
	}

}