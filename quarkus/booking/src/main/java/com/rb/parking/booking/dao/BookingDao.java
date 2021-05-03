package com.rb.parking.booking.dao;

import java.util.List;

import com.rb.parking.booking.mongo.entity.Booking;
import com.rb.parking.booking.mongo.entity.CustomerBooking;

public interface BookingDao {
	
	public Booking addNewBooking(Booking newBooking);

	public List<Booking> getAllBooking();

	public Booking findBookingByCriteria(CustomerBooking customerBooking);

	public Booking add2CurrentBooking(Booking booking);

}
