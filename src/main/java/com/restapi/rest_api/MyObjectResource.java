package com.restapi.rest_api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("objects")
public class MyObjectResource {

	MyObjectRepository repo = new MyObjectRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MyObject> getObjects() {
		
		return repo.getObjects();
	}
	// post is creating resource
	// put is for updating resource
	
	@GET
	@Path("object/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MyObject getObject(@PathParam("id") int index) {
		
		return repo.getObject( index);
	}

	@POST
	@Path("object")
	public MyObject createMyObject(MyObject obj) {
		
		System.out.println(obj.getName());
		repo.create(obj);
		return obj;
	}
}
