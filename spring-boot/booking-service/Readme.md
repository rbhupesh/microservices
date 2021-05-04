# Booking project

This project uses Spring-boot Framework.

> **_NOTE:_**  Please update mongoDB connection string in Application.properties as per your setup.


## Packaging and running the application

```shell script
mvn clean compile test package
```

This project intgrates mongoDB with Quarkus 
```shell script
java -jar .\target\booking-service-0.0.1.jar
```

## urls:

get v1: http://localhost:8080/api/v1/parking/bookings

v2:  http://localhost:8080/api/v2/parking/bookings

get by criteria
http://localhost:8080/api/v2/parking/bookings/{date}/{hour}

post: 
http://localhost:8080/api/v2/parking/bookings/{date}/{hour}/{custId}

## Sample schema


{"bookings":
	[
		
		{"id":"5f8c5d4cc8846add1760a27f","date":"14-Oct-2020","hour":"08","bookings":["c1","c2"]},
		
		{"id":"5f8c5f35c8846add1760a281","date":"14-Oct-2020","hour":"09","bookings":["c1","c3"]},
		
		{"id":"5fbf68422bf89f1a2a16560b","date":"26-Nov-2020","hour":"10","bookings":["rbhupesh","mahesh","raj","cust1","cust2","cust3","cust4"]},
		
		{"id":"5fc85d5e77ffdf60d48942bd","date":"03-Dec-2020","hour":"10","bookings":["rbhupesh","kMahesh"]},
		
		{"id":"5fd34f983a2dcb48efb3b89a","date":"11-Dec-2020","hour":"10","bookings":["sathish"]},
		
		{"id":"5fd35423fa0ce24593d63d7e","date":"11-Dec-2020","hour":"14","bookings":["cust5","cust6","cust11"]},
		
		{"id":"5fd359977f53cd363978020c","date":"12-Dec-2020","hour":"14","bookings":["cust25","cust25#2"]}
		
	]}



