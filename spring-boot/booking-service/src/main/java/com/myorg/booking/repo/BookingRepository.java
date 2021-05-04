
package com.myorg.booking.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.myorg.booking.mongo.entity.Booking;

@RepositoryRestResource(collectionResourceRel = "bookings", path = "bookings")
public interface BookingRepository extends MongoRepository<Booking, String> {

	List<Booking> findBybookingid(@Param("bookingId") String bookingId);	
}


/* on cli
	db.bookings.find({"date": "14-10-2020", "hour" :"08"})
	db.bookings.find({"date": "14-10-2020", "hour" :"08", "bookings": "c2"})
	db.bookings.find( { "bookings": { $in: [ "c1", "c2" ]} } )
 */
