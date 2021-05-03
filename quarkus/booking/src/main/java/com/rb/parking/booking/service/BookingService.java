package com.rb.parking.booking.service;

import java.util.List;

import com.rb.parking.booking.mongo.entity.Booking;
import com.rb.parking.booking.mongo.entity.CustomerBooking;

public interface BookingService {
	
	public Booking addBooking(CustomerBooking customerBooking);

	public List<Booking> getAllBookings();

	public Booking getBookingByCriteria(CustomerBooking customerBooking);

}
