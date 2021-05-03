package com.rb.parking.booking.mongo.entity;

import java.util.Arrays;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.MongoEntity;


@MongoEntity(collection="bookings")
public class Booking{
	// extends PanacheMongoEntity{

	private String id;
	
	@BsonProperty("date")
	String date = "";
	
	@BsonProperty("hour")
	String hour = "";
	
	@BsonProperty("bookings")
	String[] bookings;

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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

		sb.append("bookingid:"+id);
		sb.append(", date:"+date);
		sb.append(", hour:"+hour);
		sb.append(", bookings:"+Arrays.toString(bookings));
		
		return sb.toString() ;
	}
}