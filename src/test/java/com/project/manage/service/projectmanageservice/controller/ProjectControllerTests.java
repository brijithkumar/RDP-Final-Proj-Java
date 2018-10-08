package com.project.manage.service.projectmanageservice.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
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

import com.project.manage.service.projectmanageservice.model.ProjectVO;
import com.project.manage.service.projectmanageservice.service.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProjectController.class, secure = false)
public class ProjectControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProjectService projectService;
	
	ProjectVO projectVO1=new ProjectVO();
	ProjectVO projectVO2=new ProjectVO();
	
	@Before
	public void setUp() throws Exception {
		
		projectVO1.setProjectId(1);
		projectVO1.setProjectName("Project 1");
		projectVO1.setStartDate("01/01/2017");
		projectVO1.setEndDate("31/12/2017");
		projectVO1.setPriority(7);
		projectVO1.setCompletedStatus("Yes");
	}
	
	
	private String sampleTestProject = "{\"projectId\": 1,  \"projectName\": \"Project 1\",\"startDate\": \"30/03/2008\",\"endDate\": \"30/04/2009\", \"completedStatus\":\"Yes\","
			+ "\"priority\": 7}";
	
	@Test
	public final void testGetTaskById() throws Exception {
		Mockito.when(projectService.findProjectById(Mockito.eq(12))).thenReturn(projectVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/project/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//JSONAssert.assertEquals(sampleTestProject, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public final void testAddProject() throws Exception {
		
		Mockito.when(projectService.addProject(Mockito.any(ProjectVO.class))).thenReturn(projectVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/addProject")
				.accept(MediaType.APPLICATION_JSON).content(sampleTestProject).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public final void testUpdateProject() throws Exception {
		
		Mockito.when(projectService.updateProject(Mockito.any(ProjectVO.class))).thenReturn(projectVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/updateProject")
				.accept(MediaType.APPLICATION_JSON).content(sampleTestProject).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public final void testGetAllProjects() throws Exception {
		when(projectService.getProjects()).thenReturn(Arrays.asList(projectVO1, projectVO2));

		mockMvc.perform(get("/api/getProjects")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].projectId", is(1)));

	}

}
