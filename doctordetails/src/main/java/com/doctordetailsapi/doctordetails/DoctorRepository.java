package  com.doctordetailsapi.doctordetails;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DoctorRepository {

	List<DoctorDetails> list;
	Connection con = null;

	public DoctorRepository() {
		list = new ArrayList<DoctorDetails>();
		
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
	
	public List<DoctorDetails> getDoctors(){
		
		String sql = "select * from doc_details_tbl";
		try {
			Statement st = con.createStatement();
			ResultSet res_set = st.executeQuery(sql);
			while(res_set.next()) {
				System.out.println("hello world!");
				DoctorDetails myobj = new DoctorDetails();
				
				String doc_id = res_set.getString(1);
				String name = res_set.getString(2);
				String dept = res_set.getString(5);
				String speclization =res_set.getString(6);
				
				myobj.setDoc_id(doc_id);
				myobj.setDesignation(dept);
				myobj.setName(name);
				myobj.setSpecialization(speclization);
				
				String sql1 ="select * from doc_hospitals where doc_id = '"+doc_id+"'";
				Statement st1 = con.createStatement();
				ResultSet res_set1 = st1.executeQuery(sql1);
				ArrayList<String> doc_hosp = new ArrayList<String>();
				ArrayList<String> doc_hosp_name = new ArrayList<String>();
				while(res_set1.next()) {
					String hosp_id = res_set1.getString(2);
					System.out.println("hospital id"+hosp_id);
					doc_hosp.add(hosp_id);
					Statement st2 = con.createStatement();
					String sql3 = "select name from hospitals where id = '"+hosp_id+"'";
					ResultSet res_set3 = st2.executeQuery(sql3);
					while(res_set3.next()) {
						System.out.println("hospital name"+res_set3.getString(1));
						doc_hosp_name.add(res_set3.getString(1));
					}
					System.out.println("bye");
					System.out.println("hey..."+doc_hosp.get(0));
					myobj.setHospital_id(doc_hosp);
					myobj.setHospital_name(doc_hosp_name);
					list.add(myobj);
					System.out.println("exit loop");
				}	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}
	
	public DoctorDetails getDoctor(String index) {
		//System.out.println("sasa: "+list.get(index).getName());
		
		//return list.get(index);
		getDoctors();
		for(DoctorDetails i: list) {
			System.out.println("doctor: "+i.getDoc_id());
			if(i.getDoc_id().equals(index)) {
				return i;
			}
		}
		return null;
	}

/*	public void create(DoctorDetails obj) {
		String sql = "insert into names_tbl values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			// st.set
			st.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(obj);
		// TODO Auto-generated method stub
		
	}*/
}
