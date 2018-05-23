package com.EHS.FeedBackAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

public class FeedbackRepository {

	Connection con = null;

	public FeedbackRepository() {
		
		String url = "jdbc:mysql://localhost:3306/sample_db";
		String uname = "root";
		String password = "123";
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			
			con = DriverManager.getConnection(url,uname,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	HospitalFeedback getHospitalFeedback(String hosp_id){
		
		try {
			/*String sql1 = "select id from hospitals where name = '"+hosp_name+"'";;
			Statement st1;
			st1 = con.createStatement();
		
			ResultSet res_set1 = st1.executeQuery(sql1);
			String hosp_id = "";
		
			if(res_set1.next()) {
				hosp_id  = res_set1.getString(1); // get hospital_id
				
			}
			System.out.println("Hospital ID: "+hosp_id);
		*/
			String sql2 = "select * from review_tbl where hospital_id = '"+hosp_id+"'";;
			Statement st2;
			st2 = con.createStatement();
		
			ResultSet res_set2 = st2.executeQuery(sql2);
			Double hosp_service = 0.0,hosp_review = 0.0,overall_service = 0.0;
			int count = 0;
			
			while(res_set2.next()) {
			//	System.out.println("get:");
				hosp_service = hosp_service + res_set2.getDouble(4);
				hosp_review = hosp_review + res_set2.getDouble(5);
				overall_service = overall_service + res_set2.getDouble(6);
				count ++;
			}
			
			if(count==0) {
				HospitalFeedback feedback = new HospitalFeedback();
				feedback.setHospital_id(hosp_id);
				feedback.setOverall_review(0.0);
				feedback.setReview(0.0);
				feedback.setService_review(0.0);
				st2.close();
				return feedback;
			}
			st2.close();
		//	System.out.println("service: "+hosp_service/(1.0*count));
		//	System.out.println("review: "+hosp_review/(1.0*count));
		//	System.out.println(" overall "+overall_service*1.0/(count));
			
			HospitalFeedback feedback = new HospitalFeedback();
			feedback.setHospital_id(hosp_id);;
			feedback.setOverall_review(overall_service/(1.0*count));
			feedback.setReview(hosp_review/(1.0*count));
			feedback.setService_review(hosp_service/(1.0*count));
			
			return feedback;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		HospitalFeedback feedback = new HospitalFeedback();
		feedback.setHospital_id(hosp_id);
		feedback.setOverall_review(0.0);
		feedback.setReview(0.0);
		feedback.setService_review(0.0);
		return feedback;

	}
	
DoctorFeedback getDoctorFeedback(String doc_id){
		
	
	try {
	
		String sql2 = "select * from review_tbl where doctor_id = '"+doc_id+"'";;
		Statement st2;
		st2 = con.createStatement();
	
		ResultSet res_set2 = st2.executeQuery(sql2);
		Double hosp_service = 0.0,hosp_review = 0.0,overall_service = 0.0;
		int count = 0;
		
		while(res_set2.next()) {
	//		System.out.println("get");
			hosp_service = hosp_service + res_set2.getDouble(4);
			hosp_review = hosp_review + res_set2.getDouble(5);
			overall_service = overall_service + res_set2.getDouble(6);
			count ++;
		}
	
		if(count==0) {
			DoctorFeedback feedback = new DoctorFeedback();
			feedback.setDoctor_id(doc_id);
			feedback.setOverall_review(0.0);
			feedback.setReview(0.0);
			feedback.setService_review(0.0);
			st2.close();
			return feedback;
		}
		st2.close();
		DoctorFeedback feedback = new DoctorFeedback();
		feedback.setDoctor_id(doc_id);
		feedback.setOverall_review(overall_service/(1.0*count));
		feedback.setReview(hosp_review/(1.0*count));
		feedback.setService_review(hosp_service/(1.0*count));
		
		return feedback;
	}
	catch(Exception e) {
		e.printStackTrace();
		DoctorFeedback feedback = new DoctorFeedback();
		feedback.setDoctor_id(doc_id);
		feedback.setOverall_review(0.0);
		feedback.setReview(0.0);
		feedback.setService_review(0.0);
		return feedback;
	}
	
		
	}
}

// List hospitals based on location
// List hospitals based on 
