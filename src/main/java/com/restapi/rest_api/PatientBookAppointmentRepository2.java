package com.restapi.rest_api;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;

public class PatientBookAppointmentRepository2 {

	List<PatientBookAppointment> list;
	Connection con = null;

	public PatientBookAppointmentRepository2() {
		list = new ArrayList<PatientBookAppointment>();
		
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
	
	public List<PatientBookAppointment> getAppointments(){
		List<String>list1 = new ArrayList<String>();
		
		String sql = "select * from appointment_tbl";
		try {
			Statement st = con.createStatement();
			ResultSet res_set = st.executeQuery(sql);
			while(res_set.next()) {
				list1.add(res_set.getString(1));
				System.out.println(res_set.getString(1));
				PatientBookAppointment myobj = new PatientBookAppointment();
				myobj.setPatient_id(res_set.getString(1));
				myobj.setDoctor_id(res_set.getString(2));
				myobj.setDate_appoint(res_set.getString(3));
				myobj.setSlot_chosen(res_set.getInt(4));
				myobj.setReason_appointment(res_set.getString(5));
				list.add(myobj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	public PatientBookAppointment getAppointment(int index) {
		getAppointments();
		System.out.println("patient_id"+list.get(index).getPatient_id());
		return list.get(index);
	}

	public void createAppointment(PatientBookAppointment obj) {
		String sql = "insert into appointment_tbl values(?,?,?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,obj.getPatient_id());
			st.setString(2, obj.getDoctor_id());
			st.setString(3, obj.getDate_appoint());
			st.setInt(4, obj.getSlot_chosen());
			st.setString(5, obj.getReason_appointment());
			st.executeUpdate();
			
		} 
		
		catch(MySQLIntegrityConstraintViolationException e) {
			System.out.println("Duplicate primary keys");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(obj);
		// TODO Auto-generated method stub
		
	}
}
