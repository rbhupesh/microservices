package com.myorg.booking.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.myorg.booking.dao.BookingDao;
import com.myorg.booking.mongo.entity.Booking;
import com.myorg.booking.mongo.entity.CustomerBooking;
import com.myorg.booking.repo.BookingRepository;

public class BookingDaoImpl implements BookingDao {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Booking addNewBooking(Booking newCustomer) {
		System.out.println("##### CustomerDaoImpl.addCustomer() STARTED!!!  #####");

		Booking savedCustomer = bookingRepository.save(newCustomer);

		System.out.println("##### CustomerDaoImpl.addCustomer() executed!!!  #####");
		return savedCustomer;
	}

	@Override
	public List<Booking> getAllBooking() {
		List<Booking> bookings = bookingRepository.findAll();
		System.out.println("##### CustomerDaoImpl.getAllCustomer() executed!!!  #####");
		return bookings;
	}

	@Override
	public Booking findBookingByCriteria(CustomerBooking customerBooking) {		
		System.out.println("^^^^^^ date:"+ customerBooking.getDate()
		+" hour:"+customerBooking.getHour() +"^^^^^^");

		Query query = new Query(); 
		query.addCriteria(Criteria.where("date").is(customerBooking.getDate()))
			 .addCriteria(Criteria.where("hour").is(customerBooking.getHour()));

		List<Booking> bookings = mongoTemplate.find(query, Booking.class); 
		
		Booking booking = (bookings!=null && bookings.size()>0) ? bookings.get(0) : new Booking();
		
		System.out.println("##### CustomerDaoImpl.findBookingByCrietria() executed!!!  #####");
		return booking;
	}

	@Override
	public Booking add2CurrentBooking(Booking booking) {
		Booking updatedBooking = bookingRepository.save(booking);
		System.out.println("##### CustomerDaoImpl.add2CurrentBooking() executed!!!  #####");
		return updatedBooking;
	}
}

