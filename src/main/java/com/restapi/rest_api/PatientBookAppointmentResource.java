package com.restapi.rest_api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("appointments")
public class PatientBookAppointmentResource {

	PatientBookAppointmentRepository repo = new PatientBookAppointmentRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PatientBookAppointment> getPatientBookAppointments() {
		
		return repo.getAppointments();
	}
	// post is creating resource
	// put is for updating resource
	
	@GET
	@Path("appointment/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PatientBookAppointment getAppointment(@PathParam("id") int index) {
		
		return repo.getAppointment(index);
	}

	@POST
	@Path("appointment")
	public PatientBookAppointment createAppointment(PatientBookAppointment obj) {
		
		System.out.println(obj.getPatient_id());
		repo.createAppointment(obj);
		System.out.println("slot chosen"+obj.getSlot_chosen());
		return obj;
	}
}
