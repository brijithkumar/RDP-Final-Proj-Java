package com.project.manage.service.projectmanageservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.manage.service.projectmanageservice.model.UserVO;
import com.project.manage.service.projectmanageservice.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserVO> getUserById(@PathVariable("id") int id){
		UserVO userVO = userService.findUserById(id);
		return new ResponseEntity<UserVO>(userVO, HttpStatus.OK);

	}
	
	@RequestMapping(value="/addUser",headers = "Accept=application/json",method=RequestMethod.POST)
	public ResponseEntity<UserVO> addUser(@RequestBody UserVO userVO,UriComponentsBuilder uriComponentsBuilder){
		userVO= userService.addUser(userVO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/api/{id}").buildAndExpand(userVO.getUserId()).toUri());
		return new ResponseEntity<UserVO>(userVO, headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/getUsers")
	public List<UserVO> getUsers() {
		return userService.getUsers();
	}
	
	@PutMapping(value="/updateUser")
	public ResponseEntity<UserVO> updateUser(@Valid @RequestBody UserVO userVO){
		String jsonInput=getJSONString(userVO);
		userVO=userService.updateUser(userVO);
		return new ResponseEntity<UserVO>(userVO, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
		userService.deleteUser(id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	public String getJSONString(UserVO userVO){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(userVO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
