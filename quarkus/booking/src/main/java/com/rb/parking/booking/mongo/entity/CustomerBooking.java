package com.rb.parking.booking.mongo.entity;

public class CustomerBooking {
	
	String date = "";
	String hour = "";
	String customer = "";
	
	public CustomerBooking(String date,
	String hour,
	String customer) {
		
		 this.date = date;
		 this.hour = hour;
		 this.customer = customer;		
	}

	public String getDate() {
		return date;
	}

	public String getHour() {
		return hour;
	}

	public String getCustomer() {
		return customer;
	}
	
	

}
