package com.myorg.booking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.myorg.booking.dao.BookingDao;
import com.myorg.booking.dao.impl.BookingDaoImpl;
import com.myorg.booking.service.BookingService;
import com.myorg.booking.service.impl.BookingServiceImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(title = "Booking service API",
					version = "1.0",
					description = "Booking service used for booking parking lot"),
		servers = @Server(url = "https://www.rb-Bookstore.com",
					description = "spring-boot internal tomcat server is in use")
		)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
		
	@Bean
	public BookingService bookingService(){
		return new BookingServiceImpl();
	}
	
	@Bean
	public BookingDao bookingDao(){
		return new BookingDaoImpl();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Booking Service Started");
			System.out.println("http://localhost:8080/api/v1/parking/bookings");
			System.out.println("v2:  /api/v2/parking/bookings");
			System.out.println("get by criteria");
			System.out.println("/api/v2/parking/bookings/{date}/{hour}");
			System.out.println("post: /api/v2/parking/bookings/{date}/{hour}/{custId}");
			
		};
	}

}
