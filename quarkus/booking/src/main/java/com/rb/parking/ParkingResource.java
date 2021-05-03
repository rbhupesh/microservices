package com.rb.parking;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/parking")
public class ParkingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> hello() {
    	System.out.println("Hello parkings");
    	List<String> list = new ArrayList<String>();
    	list.add("Hello parkings");
        return list ;
    }
}