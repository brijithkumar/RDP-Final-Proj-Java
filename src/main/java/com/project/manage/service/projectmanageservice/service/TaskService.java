package com.project.manage.service.projectmanageservice.service;

import java.util.List;

import com.project.manage.service.projectmanageservice.model.ParentTaskVO;
import com.project.manage.service.projectmanageservice.model.TaskVO;

public interface TaskService {

	TaskVO findTaskById(int id);

	TaskVO addTask(TaskVO taskVO);

	List<TaskVO> getTasks();

	List<ParentTaskVO> getParentTasks();

	TaskVO updateTask(TaskVO taskVO);

}
