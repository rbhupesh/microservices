# Demo project integrating drools with spring boot.


## Packaging and running the application

```shell script
mvn clean compile test package
```

This demo shows 

1) How to use multiple drl files for storing different rules

2) How to use statefull and stateless KieSession 

## urls:

statefull kiesession

post: 

http://localhost:8080/api/v1/order

stateless kiesession

post: 

http://localhost:8080/api/v2/order

## Sample schema

{

"name":"mobile",

"cardType" : "HDFC",

"price": 11000

}


## Sample Response

{

"name":"mobile",

"cardType" : "HDFC",

"price": 11000,

"discount": 10

}

