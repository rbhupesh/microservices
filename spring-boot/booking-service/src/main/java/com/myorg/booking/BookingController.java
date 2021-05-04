package com.myorg.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.booking.mongo.entity.Booking;
import com.myorg.booking.mongo.entity.Bookings;
import com.myorg.booking.mongo.entity.CustomerBooking;
import com.myorg.booking.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	
	@GetMapping("/api/v1/parking/bookings")
	public HttpEntity<Bookings> getAllBookingsV1() {		
		System.out.println("##### BookingController.getAllBookingsV2() STARTED!!!  #####");

		Bookings bookings = new Bookings(bookingService.getAllBookings());		
		ResponseEntity<Bookings> reCustomers = new ResponseEntity<Bookings>(bookings, HttpStatus.OK);

		return reCustomers;
	}
	

	@GetMapping("/api/v2/parking/bookings")
	public HttpEntity<Bookings> getAllBookingsV2() {		
		System.out.println("##### BookingController.getAllBookingsV2() STARTED!!!  #####");

		Bookings bookings = new Bookings(bookingService.getAllBookings());		
		ResponseEntity<Bookings> reCustomers = new ResponseEntity<Bookings>(bookings, HttpStatus.OK);

		return reCustomers;
	}

	@GetMapping("/api/v2/parking/bookings/{date}/{hour}")
	public HttpEntity<Booking> getBookingByCriteria (
			@PathVariable("date") String date,
			@PathVariable("hour") String hour			
			) {
		System.out.println("##### BookingController.getBookingByCriteria() STARTED!!!  #####");

		CustomerBooking customerBooking = new CustomerBooking(date,hour,"");
		Booking booking = bookingService.getBookingByCriteria(customerBooking);

		ResponseEntity<Booking> reCustomer = new ResponseEntity<Booking>(booking, HttpStatus.OK);

		System.out.println("##### ResponseEntity customer:" + reCustomer.toString());	
		
		System.out.println("##### BookingController.getBookingByCriteria() executed!!!  #####");
		return reCustomer;
	}	

	@PostMapping("/api/v2/parking/bookings/{date}/{hour}/{custId}")
	public HttpEntity<Booking> addBooking(
			@PathVariable("date") String date,
			@PathVariable("hour") String hour,
			@PathVariable("custId") String custId
			) {
		System.out.println("\n##### BookingController.addBooking() STARTED!!!  #####");

		CustomerBooking customerBooking = new CustomerBooking(date,hour,custId);
		Booking booking = bookingService.addBooking(customerBooking);

		ResponseEntity<Booking> reCustomer = new ResponseEntity<Booking>(booking, HttpStatus.OK);

		System.out.println("##### ResponseEntity customer:" + reCustomer.toString());		
		System.out.println("##### BookingController.addBooking() executed!!!  #####");
		return reCustomer;
	}	
}

