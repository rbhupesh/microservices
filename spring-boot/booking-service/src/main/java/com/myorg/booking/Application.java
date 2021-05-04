package com.myorg.booking;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

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
		servers = @Server(url = "https://www.rb-parking.com",
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
	MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {		
			
			inspectBeansV2(ctx);
			
			
			System.out.println("***************************************************************************");
			System.out.println("*Booking Service Started");
			System.out.println("***************************************************************************");
		};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//inspectBeansV1(ctx);
	
	
	private void inspectBeansV1(ApplicationContext ctx) {
		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
	
		for (String beanName : beanNames) {						
				System.out.println(beanName);					
		}
		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
	}
	
	
	
	
	
	private void inspectBeansV2(ApplicationContext ctx) {
		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
	
		for (String beanName : beanNames) {
			if (beanName.contains("transaction") || beanName.contains("Transaction")) {				
				System.out.println(beanName);			
			}			
		}
		
		System.out.println("***************************************************************************");
		
		for (String beanName : beanNames) {
			if (beanName.contains("transaction") || beanName.contains("Transaction")) {
				System.out.println(ctx.getBean(beanName).getClass());	
			}			
		}
		
		
		System.out.println("***************************************************************************");
		
		for (String beanName : beanNames) {
			if (beanName.contains("mongo") || beanName.contains("Mongo")) {
				System.out.println(beanName);	
			}			
		}

		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
	}
}



/*
 * 
 * System.out.println("*get http://localhost:8080/api/v1/parking/bookings");
			System.out.println("*get http://localhost:8080//api/v2/parking/bookings/14-Oct-2020/08");
			System.out.println("*post http://localhost:8080/api/v2/parking/bookings/{date}/{hour}/{custId}");

			System.out.println("*post http://localhost:8080/api/v2/transactional/parking/bookings/{date}/{hour}/{custId}");
			System.out.println("*post http://localhost:8080/api/v2/nontransactional/parking/bookings/{date}/{hour}/{custId}");
 * 
 * 
  @Bean
	MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
 */



/*

get http://localhost:8080/api/v1/parking/bookings
get http://localhost:8080//api/v2/parking/bookings/14-Oct-2020/08

post http://localhost:8080/api/v2/parking/bookings/{date}/{hour}/{custId}
post http://localhost:8080/api/v2/transactional/parking/bookings/{date}/{hour}/{custId}
post http://localhost:8080/api/v2/nontransactional/parking/bookings/{date}/{hour}/{custId}



*/


