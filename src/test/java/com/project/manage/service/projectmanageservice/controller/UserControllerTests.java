package com.project.manage.service.projectmanageservice.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.manage.service.projectmanageservice.model.UserVO;
import com.project.manage.service.projectmanageservice.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	UserVO userVO1 = new UserVO();
	UserVO userVO2 = new UserVO();
	private String sample =null;
	
	@Before
	public void setUp() throws Exception {

		userVO1 = new UserVO();
		userVO1.setUserId(1);
		userVO1.setEmployeeId(12);
		userVO1.setFirstName("FirstName");
		userVO1.setLastName("LastName");
		

		userVO2 = new UserVO();
		userVO2.setUserId(2);
		userVO2.setEmployeeId(2);
		userVO2.setFirstName("First Name 2");
		userVO2.setLastName("Last Name 2");
		
		sample=getJSONString(userVO1);
	}
	
	private String sampleTestUser = "{\"employeeId\": 12, "
			+ "\"userId\": 0, \"firstName\": \"FirstName\", \"lastName\":\"LastName\"}";
	
	
	
	
	public String getJSONString(UserVO userVO){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(userVO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	//private String sampleInput="{userId:1,firstName:Brijith,lastName:Kumar,employeeId:1}";
	
	@Test
	public final void testGetUserById() throws Exception {
		Mockito.when(userService.findUserById(Mockito.eq(12))).thenReturn(userVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//JSONAssert.assertEquals(sampleTestUser, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public final void testAddUser() throws Exception {
		
		Mockito.when(userService.addUser(Mockito.any(UserVO.class))).thenReturn(userVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/addUser")
				.accept(MediaType.APPLICATION_JSON).content(sample).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public final void testUpdateUser() throws Exception {
		
		Mockito.when(userService.updateUser(Mockito.any(UserVO.class))).thenReturn(userVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/updateUser")
				.accept(MediaType.APPLICATION_JSON).content(sample).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public final void testDeleteUser() throws Exception {
		
		Mockito.doNothing().when(userService).deleteUser(Mockito.eq(1));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/deleteUser/1").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}
	
	@Test
	public final void testGetAllUsers() throws Exception {
		when(userService.getUsers()).thenReturn(Arrays.asList(userVO1, userVO2));

		mockMvc.perform(get("/api/getUsers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].userId", is(1)));

		verify(userService, times(1)).getUsers();
	}


}
