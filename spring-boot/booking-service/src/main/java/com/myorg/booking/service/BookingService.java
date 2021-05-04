package com.myorg.booking.service;

import java.util.List;

import com.myorg.booking.mongo.entity.Booking;
import com.myorg.booking.mongo.entity.CustomerBooking;

public interface BookingService {
	
	public Booking addBooking(CustomerBooking customerBooking);

	public List<Booking> getAllBookings();

	public Booking getBookingByCriteria(CustomerBooking customerBooking);

}
