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
import com.project.manage.service.projectmanageservice.model.ProjectVO;
import com.project.manage.service.projectmanageservice.service.ProjectService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	@GetMapping(value = "/project/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectVO> getProjectById(@PathVariable("id") int id){
		ProjectVO projectVO = projectService.findProjectById(id);
		return new ResponseEntity<ProjectVO>(projectVO, HttpStatus.OK);

	}
	
	@PostMapping(value="/addProject")
	public ResponseEntity<ProjectVO> addProject(@RequestBody ProjectVO projectVO,UriComponentsBuilder uriComponentsBuilder){
		
		projectVO= projectService.addProject(projectVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/api/project/{id}").buildAndExpand(projectVO.getProjectId()).toUri());
		return new ResponseEntity<ProjectVO>(projectVO, headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/getProjects")
	public List<ProjectVO> getProjects() {
		return projectService.getProjects();
	}
	
	@PutMapping(value="/updateProject")
	public ResponseEntity<ProjectVO> updateProject(@Valid @RequestBody ProjectVO projectVO){
		projectVO=projectService.updateProject(projectVO);
		return new ResponseEntity<ProjectVO>(projectVO, HttpStatus.OK);
	}
	
	
}
