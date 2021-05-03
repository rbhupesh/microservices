
package com.rb.parking.booking.dao.repo;

import java.util.List;

import javax.inject.Singleton;

import com.rb.parking.booking.mongo.entity.Booking;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;

@Singleton
public class BookingRepository implements PanacheMongoRepositoryBase<Booking,Long> {

	List<Booking> findBybookingid( String bookingId){
		return null;		
	}
	
}

/* on cli
	db.bookings.find({"date": "14-10-2020", "hour" :"08"})
	db.bookings.find({"date": "14-10-2020", "hour" :"08", "bookings": "c2"})
	db.bookings.find( { "bookings": { $in: [ "c1", "c2" ]} } )
 */
