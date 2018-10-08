package com.project.manage.service.projectmanageservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.manage.service.projectmanageservice.dao.ParentTaskRepository;
import com.project.manage.service.projectmanageservice.dao.TaskRepository;
import com.project.manage.service.projectmanageservice.entity.ParentTask;
import com.project.manage.service.projectmanageservice.entity.Project;
import com.project.manage.service.projectmanageservice.entity.Task;
import com.project.manage.service.projectmanageservice.entity.User;
import com.project.manage.service.projectmanageservice.model.ParentTaskVO;
import com.project.manage.service.projectmanageservice.model.TaskVO;
import com.project.manage.service.projectmanageservice.util.ProjectManagementUtility;

@Service
public class TaskServiceImplementation implements TaskService{
	
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	ParentTaskRepository parentTaskRepository;

	@Transactional(readOnly=true)
	@Override
	public TaskVO findTaskById(int id) {
		Task task=taskRepository.getOne(id);
		TaskVO taskVO=new TaskVO();
		taskVO.setTaskId(task.getTaskId());
		taskVO.setTaskName(task.getTaskName());
		taskVO.setTaskStatus(task.getTaskStatus());
		taskVO.setStartDate(task.getStartDate());
		taskVO.setEndDate(task.getEndDate());
		taskVO.setPriority(task.getPriority());
		taskVO.setProject(task.getProject());
		taskVO.setTaskOwner(task.getTaskOwner());
		taskVO.setParentTask(task.getParentTask());
		//ProjectManagementUtility.copyObjects(task, taskVO);
		return taskVO;
	}

	@Transactional(readOnly=false)
	@Override
	public TaskVO addTask(TaskVO taskVO) {
		Task task=new Task();
		task.setParentTask(new ParentTask());
		task.setProject(new Project());
		task.setTaskOwner(new User());
		ProjectManagementUtility.copyObjects(taskVO, task);
		if(taskVO.isParentTaskSelectionModel()){
			parentTaskRepository.save(task.getParentTask());
		}
		taskRepository.save(task);
		ProjectManagementUtility.copyObjects(task, taskVO);
		return taskVO;
	}

	@Transactional(readOnly=true)
	@Override
	public List<TaskVO> getTasks() {
		List<Task> tasks=taskRepository.findAll();
		List<TaskVO> taskVOs=new ArrayList<TaskVO>();
		for(Task task:tasks){
			TaskVO taskVO=new TaskVO();
			ProjectManagementUtility.copyObjects(task, taskVO);
			taskVOs.add(taskVO);
		}
		return taskVOs;
	}

	@Transactional(readOnly=true)
	@Override
	public List<ParentTaskVO> getParentTasks() {
		List<ParentTask> parentTasks=parentTaskRepository.findAll();
		List<ParentTaskVO> parentTaskVOs=new ArrayList<ParentTaskVO>();
		for(ParentTask parentTask:parentTasks){
			ParentTaskVO parentTaskVO=new ParentTaskVO();
			ProjectManagementUtility.copyObjects(parentTask, parentTaskVO);
			parentTaskVOs.add(parentTaskVO);
		}
		return parentTaskVOs;
	}
	
	@Transactional(readOnly=false)
	@Override
	public TaskVO updateTask(TaskVO taskVO) {
		Task task=new Task();
		ProjectManagementUtility.copyObjects(taskVO, task);
		taskRepository.saveAndFlush(task);
		ProjectManagementUtility.copyObjects(task, taskVO);
		return taskVO;
	}

	

}
