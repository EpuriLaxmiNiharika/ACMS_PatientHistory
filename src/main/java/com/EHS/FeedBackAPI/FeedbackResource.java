package com.EHS.FeedBackAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("feedback")
public class FeedbackResource {

	FeedbackRepository repo = new FeedbackRepository();
	
	@GET
	@Path("hospital/{id}") // get hospital feedback
	@Produces(MediaType.APPLICATION_JSON)
	public HospitalFeedback getHospitalFeedback(@PathParam("id") String hospital_id) {
		
		return repo.getHospitalFeedback(hospital_id);
	}
	
	
	@GET
	@Path("doctor/{id}") // get doctor feedback
	@Produces(MediaType.APPLICATION_JSON)
	public DoctorFeedback getDoctorFeedback(@PathParam("id") String doctor_id) {
		
		return repo.getDoctorFeedback(doctor_id);
	}
	
	
	@POST
	@Path("submitfeedback")
	public FeedbackDetails createDoctor(FeedbackDetails obj) {
		
		return null;
	}
}