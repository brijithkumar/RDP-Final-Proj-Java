package com.project.manage.service.projectmanageservice.model;

import java.lang.reflect.Field;

public class UserVO{

	
	private int userId;
	private int employeeId;
	private String firstName;
	private String lastName;
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
/*	@Override
	public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append(getClass().getName());
	  sb.append(": ");
	  for (Field f : getClass().getDeclaredFields()) {
	    sb.append(f.getName());
	    sb.append("=");
	    try {
			sb.append(f.get(this));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	sb.append(", ");
	    
	  }
	  return sb.toString();
	}*/
	
/*	@Override
	public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append("User [");
	  int i=0;
	  for (Field f : getClass().getDeclaredFields()) {
		  i++;
	    sb.append(f.getName());
	    sb.append("=");
	    try {
			sb.append(f.get(this));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(i!=getClass().getDeclaredFields().length){
	    sb.append(", ");
	    }
	  }
	  sb.append("]");
	  return sb.toString();
	}*/
	
	
}
