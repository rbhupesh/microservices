package com.myorg.booking.dao;

import java.util.List;

import com.myorg.booking.mongo.entity.Booking;
import com.myorg.booking.mongo.entity.CustomerBooking;

public interface BookingDao {
	
	public Booking addNewBooking(Booking newBooking);

	public List<Booking> getAllBooking();

	public Booking findBookingByCriteria(CustomerBooking customerBooking);

	public Booking add2CurrentBooking(Booking booking);

}
