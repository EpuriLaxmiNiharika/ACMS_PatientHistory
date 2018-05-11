package com.doctordetailsapi.doctordetails;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("doctors")
public class DoctorResource {

	DoctorRepository repo = new DoctorRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorDetails> getDoctors() {
		
		return repo.getDoctors();
	}
	// post is creating resource
	// put is for updating resource
	
	@GET
	@Path("doctor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DoctorDetails getDoctor(@PathParam("id") String index) {
		
		return repo.getDoctor(index);
	}
/*
	@POST
	@Path("dotor")
	public DoctorDetails createDoctor(DoctorDetails obj) {
		
		System.out.println(obj.getName());
		repo.create(obj);
		return obj;
	}*/
}
