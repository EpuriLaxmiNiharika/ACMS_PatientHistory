package com.restapi.rest_api;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MyObjectRepository {

	List<MyObject> list;
	Connection con = null;

	public MyObjectRepository() {
		list = new ArrayList<MyObject>();
		
	/*	MyObject a1 = new MyObject();
		a1.setName("niharika");
		a1.setPoints(10);
		
		MyObject a2 = new MyObject();
		a2.setName("chandana");
		a2.setPoints(20);
		
		list.add(a1);
		list.add(a2);*/
		//"jdbc:mysql://localhost:3306/sample_db";
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
	
	public List<MyObject> getObjects(){
		List<String>list1 = new ArrayList<String>();
		
		String sql = "select * from names_tbl";
		try {
			Statement st = con.createStatement();
			ResultSet res_set = st.executeQuery(sql);
			while(res_set.next()) {
				list1.add(res_set.getString(1));
				System.out.println(res_set.getString(1));
				MyObject myobj = new MyObject();
				myobj.setName(res_set.getString(1));
				myobj.setPoints(100);
				list.add(myobj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	MyObject a1 = new MyObject();
		a1.setName("niharika");
		a1.setPoints(10);
		
		MyObject a2 = new MyObject();
		a2.setName("chandana");
		a2.setPoints(20);
		
		list.add(a1);
		list.add(a2); */
		return list;
	}
	
	public MyObject getObject(int index) {
		System.out.println("sasa: "+list.get(index).getName());
		return list.get(index);
	}

	public void create(MyObject obj) {
		String sql = "insert into names_tbl values(?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,obj.getName());
			st.setString(2, "nihii");
			st.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(obj);
		// TODO Auto-generated method stub
		
	}
}
