package com.myorg.booking.mongo.entity;

import java.util.List;

public class Bookings {
	
	List<Booking> bookings;
	
	public Bookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public List<Booking> getBookings() {
		return bookings;
	}

}
