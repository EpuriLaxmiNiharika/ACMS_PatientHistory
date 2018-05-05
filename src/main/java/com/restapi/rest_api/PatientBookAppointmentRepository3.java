package com.restapi.rest_api;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;

public class PatientBookAppointmentRepository3 {

	   public static final String SLOTS_AVAILABLE_TBL_COL1 = "doctor_id"; // DOC MOBILE NUMBER
	    public static final String SLOTS_AVAILABLE_TBL_COL2 = "date";
	   // public static final String SLOTS_AVAILABLE_TBL_COL3 = "slot1";
	   // public static final String SLOTS_AVAILABLE_TBL_COL4 = "slots_avail_slot2";
	   // public static final String SLOTS_AVAILABLE_TBL_COL5 = "slots_avail_slot3";
	   // public static final String SLOTS_AVAILABLE_TBL_COL6 = "slots_avail_slot4";
	    
	List<PatientBookAppointment> list;
	Connection con = null;

	public PatientBookAppointmentRepository3() {
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
		// before inserting into the database we should check that if the slots are available or not.
		
		// first check if the chosen (doctor, date available or not)
		
		System.out.println(obj.getDate_appoint());
		String doc_id = obj.getDoctor_id();
		String date = obj.getDate_appoint();
		String sql_find = "select * from appointment_tbl where doctor_id='" + doc_id + "'" + " and appointment_date='"+ date + "'";
		//String sql_find = "select * from appointment_tbl where doctor_id='" + doc_id + "'";
		boolean entry_found = false;
		try {
			Statement st = con.createStatement();
			ResultSet res_set = st.executeQuery(sql_find);
			if(res_set.next()) { 
				System.out.println("--: "+res_set.getString(0));
				entry_found = true;
			}
			//rs = st.executeQuery("select * from EmployeeAttendanceSystem.Employee where username='" + userid + "'");
			if(!entry_found) {
				System.out.println("hey there!!");
				
				String sql = "insert into slots_table values(?,?,?,?,?,?,?,?,?)";
					
			//	String sql = "insert into slots_table(doctor_id,date,slot+"+obj.getSlot_chosen()+")" + "values('" + obj.getDoctor_id() + "', '" + obj.getDate_appoint()+ "',1)";                          
			
				PreparedStatement st1 = con.prepareStatement(sql);
				st1.setString(1,obj.getDoctor_id());
				st1.setString(2, obj.getDate_appoint());
				st1.setInt(2+obj.getSlot_chosen(), 1);
				
				int i = 1 + obj.getSlot_chosen();	
				
				while(i>=3) {
					st1.setInt(i, 0);
					i--;
				}
				
				i = 3+obj.getSlot_chosen();
				while(i<=9) {
					st1.setInt(i, 0);
					i++;
				}
				st1.executeUpdate();
								
				String sql1 = "insert into appointment_tbl values(?,?,?,?,?)";
					PreparedStatement st2 = con.prepareStatement(sql1);
					
					st2.setString(1,obj.getPatient_id());
					st2.setString(2, obj.getDoctor_id());
					st2.setString(3, obj.getDate_appoint());
					st2.setInt(4, obj.getSlot_chosen());
					st2.setString(5, obj.getReason_appointment());
					st2.executeUpdate();
				
			}
			
			else {
				System.out.println("Inside else loop");
					String sql = "insert into appointment_tbl values(?,?,?,?,?)";
				try {
					PreparedStatement st1 = con.prepareStatement(sql);
					
					st1.setString(1,obj.getPatient_id());
					st1.setString(2, obj.getDoctor_id());
					st1.setString(3, obj.getDate_appoint());
					st1.setInt(4, obj.getSlot_chosen());
					st1.setString(5, obj.getReason_appointment());
					st1.executeUpdate();
					
				} 
				
				catch(MySQLIntegrityConstraintViolationException e) {
					System.out.println("Duplicate primary keys");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(obj);
			}
		}
		
		catch(Exception e) {
			
		}
		
		// TODO Auto-generated method stub
		
	}
}
