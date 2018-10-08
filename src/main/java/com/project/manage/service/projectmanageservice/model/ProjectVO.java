package com.project.manage.service.projectmanageservice.model;

import java.lang.reflect.Field;

import com.project.manage.service.projectmanageservice.entity.User;

public class ProjectVO {
	
	private int projectId;
	private String projectName;
	private int priority;
	private boolean enableProjectDate;
	private String startDate;
	private String endDate;
	private User manager;
	private int numberOfTasks;
	private int numberOfCompletedTasks;
	private String completedStatus;
	
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public boolean isEnableProjectDate() {
		return enableProjectDate;
	}
	public void setEnableProjectDate(boolean enableProjectDate) {
		this.enableProjectDate = enableProjectDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	public int getNumberOfTasks() {
		return numberOfTasks;
	}
	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}
	public int getNumberOfCompletedTasks() {
		return numberOfCompletedTasks;
	}
	public void setNumberOfCompletedTasks(int numberOfCompletedTasks) {
		this.numberOfCompletedTasks = numberOfCompletedTasks;
	}
	public String getCompletedStatus() {
		return completedStatus;
	}
	public void setCompletedStatus(String completedStatus) {
		this.completedStatus = completedStatus;
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
			e.printStackTrace();
		}
	    sb.append(", ");
	  }
	  return sb.toString();
	}*/
	

}
