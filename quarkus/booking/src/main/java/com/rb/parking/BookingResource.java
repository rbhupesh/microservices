package com.rb.parking;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.rb.parking.booking.mongo.entity.Booking;
import com.rb.parking.booking.mongo.entity.Bookings;
import com.rb.parking.booking.mongo.entity.CustomerBooking;
import com.rb.parking.booking.service.BookingService;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/parkings")
public class BookingResource {
	
	@Inject
	BookingService bookingService;
	
	@GET
	@Path("api/v2/bookings")
	public Response getAllBookingsV2() {		
		System.out.println("##### BookingResource.getAllBookingsV2() STARTED!!!  #####");

		Bookings bookings = new Bookings(bookingService.getAllBookings());		

		//ResponseEntity<Bookings> reCustomers = new ResponseEntity<Bookings>(bookings, HttpStatus.OK);
		
		System.out.println("##### ResponseEntity booking:#\n" + bookings.toString());
		System.out.println("##### BookingResource.getAllBookingsV2() executed!!!  #####");
		return Response.ok(bookings).build();
	}
	
	@GET
	@Path("/api/v2/bookings/{date}/{hour}")
	public Response getBookingByCriteria (
			@PathParam("date") String date,
			@PathParam("hour") String hour			
			) {
		System.out.println("##### BookingResource.getBookingByCriteria() STARTED!!!  #####");

		CustomerBooking customerBooking = new CustomerBooking(date,hour,"");
		Booking booking = bookingService.getBookingByCriteria(customerBooking);

		//ResponseEntity<Booking> reCustomer = new ResponseEntity<Booking>(booking, HttpStatus.OK);

		System.out.println("##### ResponseEntity booking:#\n" + booking.toString());		
		System.out.println("##### BookingResource.getBookingByCriteria() executed!!!  #####");
		return Response.ok(booking).build();
	}
	
	@GET
	@Path("/api/v2/bookings/{date}/{hour}/{custId}")
	public Response addBooking(
			@PathParam("date") String date,
			@PathParam("hour") String hour,
			@PathParam("custId") String custId
			) {
		System.out.println("\n##### BookingResource.addBooking() STARTED!!!  #####");

		CustomerBooking customerBooking = new CustomerBooking(date,hour,custId);
		Booking booking = bookingService.addBooking(customerBooking);

		System.out.println("##### ResponseEntity booking:#\n" + booking.toString());		
		System.out.println("##### BookingResource.addBooking() executed!!!  #####");
		return Response.ok(booking).build();
	}
		
    @GET    
    public String hello() {
    	System.out.println("Hello Bookings!!!");
        return "Hello Bookings";
    }
}