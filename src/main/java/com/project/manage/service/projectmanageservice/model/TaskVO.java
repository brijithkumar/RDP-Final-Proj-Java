package com.project.manage.service.projectmanageservice.model;

import com.project.manage.service.projectmanageservice.entity.ParentTask;
import com.project.manage.service.projectmanageservice.entity.Project;
import com.project.manage.service.projectmanageservice.entity.User;

public class TaskVO{

	private int taskId;
	private String taskName;
	private String startDate;
	private String endDate;
	private int priority;
	private String taskStatus;
	private Project project;
	private User taskOwner ; 
	private ParentTask parentTask;
	private boolean parentTaskSelectionModel;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public User getTaskOwner() {
		return taskOwner;
	}
	public void setTaskOwner(User taskOwner) {
		this.taskOwner = taskOwner;
	}
	public ParentTask getParentTask() {
		return parentTask;
	}
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}
	public boolean isParentTaskSelectionModel() {
		return parentTaskSelectionModel;
	}
	public void setParentTaskSelectionModel(boolean parentTaskSelectionModel) {
		this.parentTaskSelectionModel = parentTaskSelectionModel;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	
}
