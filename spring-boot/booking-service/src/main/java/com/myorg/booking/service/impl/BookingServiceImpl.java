package com.myorg.booking.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myorg.booking.dao.BookingDao;
import com.myorg.booking.mongo.entity.Booking;
import com.myorg.booking.mongo.entity.CustomerBooking;
import com.myorg.booking.service.BookingService;

public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Booking addBooking(CustomerBooking customerBooking) {
		System.out.println("##### BookingServiceImpl.addBooking() STARTED!!!  #####");

		if(!isValid(customerBooking.getDate()))
			return new Booking();
		Booking booking = bookingDao.findBookingByCriteria(customerBooking);

		if (booking != null) {	
			String [] bookings = booking.getBookings();
			System.out.println("BookingServiceImpl.addBooking() bookings :"+Arrays.toString(bookings));

			if (bookings != null && bookings.length > 0) {
				//add customer to array and update the document
				System.out.println("BookingServiceImpl.addBooking()  bookings.length > 0");

				String[] currentBooking =  Arrays.copyOf(booking.getBookings(), booking.getBookings().length + 1); 
				currentBooking[booking.getBookings().length] = customerBooking.getCustomer();
				booking.setBookings(currentBooking);
				System.out.println("BookingServiceImpl.addBooking()  booking object"+booking);
				booking = bookingDao.add2CurrentBooking(booking);
			}
			else {
				//add new document
				String[] bookingArray = {customerBooking.getCustomer()};
				Booking newBooking = new Booking(customerBooking.getDate(),customerBooking.getHour(),bookingArray);
				booking = bookingDao.addNewBooking(newBooking);
			}
		}

		System.out.println("##### BookingServiceImpl.addBooking() executed!!!  #####");
		return booking;
	}
	
	

	@Override
	public List<Booking> getAllBookings() {
		List<Booking> bookings = bookingDao.getAllBooking();
		System.out.println("##### BookingServiceImpl.getAllBookings() executed!!!  #####");
		return bookings;
	}

	@Override
	public Booking getBookingByCriteria(CustomerBooking customerBooking) {		
		if(!isValid(customerBooking.getDate()))
			return new Booking();

		Booking booking = bookingDao.findBookingByCriteria(customerBooking);
		System.out.println("##### BookingServiceImpl.getBookingByCrietria() executed!!!  #####");
		return booking;
	}

	@Override
	public List<Booking> add2BookingsNonTransactional(CustomerBooking customerBooking) {
				
		Booking booking1 = addBooking( customerBooking);

		boolean condition = true;
		if (condition) {
			System.out.println("add2Bookings something went wrong ");
			throw new RuntimeException("Something went wrong");			
		}

		CustomerBooking customerBooking2 = new CustomerBooking(
				customerBooking.getDate(), 
				customerBooking.getHour(), 
				customerBooking.getDate()+"#2");
		
		//save second booking
		Booking booking2 = addBooking( customerBooking2);

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);

		return bookings;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Booking> add2BookingsTransactional(CustomerBooking customerBooking) {
		Booking booking1 = addBooking( customerBooking);

		boolean condition = true;
			
		CustomerBooking customerBooking2 = new CustomerBooking(
				customerBooking.getDate(), 
				customerBooking.getHour(), 
				customerBooking.getCustomer()+"#2");
		
		Booking booking2 = addBooking( customerBooking2);
		
		
		if (condition) {
			System.out.println("add2Bookings something went wrong ");
			throw new RuntimeException("Something went wrong");			
		}

		List<Booking> bookings = new ArrayList<>();
		bookings.add(booking1);
		bookings.add(booking2);

		return bookings;
	}	
	

	private boolean isValid(String dateStr) {
		DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("Error invalidDate!!!!!");
			return false;
		}
		return true;
	}


}
