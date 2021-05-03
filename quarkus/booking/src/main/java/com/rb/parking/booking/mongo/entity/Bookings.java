package com.rb.parking.booking.mongo.entity;

import java.util.List;

public class Bookings {
	
	List<Booking> bookings;
	
	public Bookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public List<Booking> getBookings() {
		return bookings;
	}
	
	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		for (Booking booking : bookings) {
			strBuff.append(booking.toString());
		}
		return strBuff.toString();
	}

}
