package com.myorg.booking.mongo.entity;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
public class Booking {

	@Id
	String bookingid;

	String date = "";
	String hour = "";
	String[] bookings;
	
	@Version
	int version;

	public Booking() {		
	}

	public Booking(String date,
			String hour,
			String[] bookings
			) {	
		this.date = date;
		this.hour = hour;
		this.bookings = bookings;
	}

	

	public String getBookingid() {
		return bookingid;
	}

	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String[] getBookings() {
		return bookings;
	}

	public void setBookings(String[] bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(",bookingid:"+bookingid);
		sb.append(" date: "+date);
		sb.append(", hour: "+hour);
		sb.append(", bookings: "+Arrays.toString(bookings));
		
		return sb.toString() ;
	}
}