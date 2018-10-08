package com.project.manage.service.projectmanageservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.manage.service.projectmanageservice.model.ParentTaskVO;
import com.project.manage.service.projectmanageservice.model.TaskVO;
import com.project.manage.service.projectmanageservice.service.TaskService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	
	@GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskVO> getTaskById(@PathVariable("id") int id){
		TaskVO taskVO = taskService.findTaskById(id);
		return new ResponseEntity<TaskVO>(taskVO, HttpStatus.OK);
	}
	
	@PostMapping(value="/addTask")
	public ResponseEntity<TaskVO> addTask(@RequestBody TaskVO taskVO,UriComponentsBuilder uriComponentsBuilder){
		taskVO= taskService.addTask(taskVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/api/task/{id}").buildAndExpand(taskVO.getTaskId()).toUri());
		return new ResponseEntity<TaskVO>(taskVO, headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/getTasks")
	public List<TaskVO> getTasks() {
		return taskService.getTasks();
	}
	
	@GetMapping(value="/getParentTasks")
	public List<ParentTaskVO> getParentTasks() {
		return taskService.getParentTasks();
	}
	
	@PutMapping(value="/updateTask")
	public ResponseEntity<TaskVO> updateTask(@Valid @RequestBody TaskVO taskVO){
		taskVO=taskService.updateTask(taskVO);
		return new ResponseEntity<TaskVO>(taskVO, HttpStatus.OK);
	}
	
	
}
