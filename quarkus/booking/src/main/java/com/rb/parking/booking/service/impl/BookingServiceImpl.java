package com.rb.parking.booking.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import com.rb.parking.booking.dao.BookingDao;
import com.rb.parking.booking.mongo.entity.Booking;
import com.rb.parking.booking.mongo.entity.CustomerBooking;
import com.rb.parking.booking.service.BookingService;

@Singleton
public class BookingServiceImpl implements BookingService {
	
	@Inject
	BookingDao bookingDao;

	@Override
	@Transactional
	public Booking addBooking(CustomerBooking customerBooking) {
		System.out.println("##### BookingServiceImpl.addBooking() STARTED!!!  #####");
		
		if(!isValid(customerBooking.getDate()))
			//TODO throw exception 
			return new Booking();
		
		Booking booking = bookingDao.findBookingByCriteria(customerBooking);
		
		if (booking != null) {			
			String [] bookings = booking.getBookings();
			
			if (bookings != null && bookings.length > 0) {
				//add booking to array and update the document
				String[] currentBooking =  Arrays.copyOf(booking.getBookings(), booking.getBookings().length + 1); 
				currentBooking[booking.getBookings().length] = customerBooking.getCustomer();
				booking.setBookings(currentBooking);
				
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
		System.out.println("##### BookingServiceImpl.getAllBookings() started!!!  #####");
		
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
