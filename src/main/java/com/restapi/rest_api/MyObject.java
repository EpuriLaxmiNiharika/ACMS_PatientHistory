package com.restapi.rest_api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class MyObject {

	private String name;
	private int points;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public void create(MyObject obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		return "MyObject [name=" + name + ", points=" + points + "]";
	}
	
	
}
