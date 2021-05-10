package com.rb.saga;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.rb.saga.domain.OrchestratorRequestDTO;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/ecommerce")
public class OrderResource {
	
	@Inject
	OrchestratorService orchestratorService;
	
	@GET
	@Path("/api/v2/orders/{userId}/{productId}")
	public Response addOrder(
			@PathParam("userId") String userId,
			@PathParam("productId") String productId
			) {
		System.out.println("\n##### OrderResource.addOrder() STARTED!!!  #####");

		OrchestratorRequestDTO requestDTO = new OrchestratorRequestDTO();
		requestDTO.setUserId(userId);
		requestDTO.setProductId(productId);
		
		orchestratorService.orderProduct(requestDTO);

		System.out.println("##### ResponseEntity booking:#\n" + requestDTO);		
		System.out.println("##### OrderResource.addOrder() executed!!!  #####");
		return Response.ok(requestDTO).build();
	}
		
    @GET    
    public String hello() {
    	System.out.println("Hello Bookings!!!");
        return "Hello Bookings";
    }
}