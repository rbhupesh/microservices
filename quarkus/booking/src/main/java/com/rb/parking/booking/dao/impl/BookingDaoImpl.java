package com.rb.parking.booking.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bson.Document;

import com.rb.parking.booking.dao.BookingDao;
import com.rb.parking.booking.dao.repo.BookingRepository;
import com.rb.parking.booking.mongo.entity.Booking;
import com.rb.parking.booking.mongo.entity.CustomerBooking;

@Singleton
public class BookingDaoImpl implements BookingDao {

	@Inject
	BookingRepository bookingRepository;
	
	@Override
	public List<Booking> getAllBooking() {
		System.out.println("##### BookingDaoImpl.getAllBooking() started!!!  #####");
	
		List<Booking> bookings = bookingRepository.listAll();
	
		System.out.println("##### BookingDaoImpl.getAllBooking() executed!!!  #####");
		return bookings;
	}

	@Override
	public Booking findBookingByCriteria(CustomerBooking customerBooking) {		
		System.out.println("##### BookingDaoImpl.findBookingByCriteria() started!!!  #####");
		System.out.println("^^^^^^ date:"+ customerBooking.getDate()
		+" hour:"+customerBooking.getHour() +"^^^^^^");
		
		Document query = new Document();
		query.append("date", customerBooking.getDate());
		query.append("hour", customerBooking.getHour());
		
		List<Booking> bookings = bookingRepository.list(query);	
		
		Booking booking = (bookings!=null && bookings.size()>0) ? bookings.get(0) : new Booking();
		
		System.out.println("##### BookingDaoImpl.findBookingByCrietria() executed!!!  #####"
				+"\n -->>Booking.getId() "+booking.getId());
		return booking;
	}
	
	@Override
	public Booking addNewBooking(Booking newBooking) {
		System.out.println("##### BookingDaoImpl.addNewBooking() STARTED!!!  #####"
				+"\n -->>newBooking.getId() "+newBooking.getId());

		bookingRepository.persist(newBooking);

		System.out.println("##### BookingDaoImpl.addNewBooking() executed!!!  #####");
		return newBooking;
	}

	@Override
	public Booking add2CurrentBooking(Booking booking) {
		System.out.println("##### BookingDaoImpl.add2CurrentBooking() STARTED!!!  #####"
				+"\n -->>"+ booking);

		bookingRepository.update(booking);
		
		System.out.println("##### BookingDaoImpl.add2CurrentBooking() executed!!!  #####");
		return booking;
	}
}