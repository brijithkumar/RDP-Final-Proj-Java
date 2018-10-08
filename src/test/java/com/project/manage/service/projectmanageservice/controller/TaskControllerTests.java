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

import com.project.manage.service.projectmanageservice.entity.ParentTask;
import com.project.manage.service.projectmanageservice.model.ParentTaskVO;
import com.project.manage.service.projectmanageservice.model.TaskVO;
import com.project.manage.service.projectmanageservice.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class, secure = false)
public class TaskControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskService taskService;
	
	TaskVO taskVO1=new TaskVO();
	TaskVO taskVO2=new TaskVO();
	
	ParentTaskVO parentTaskVO1=new ParentTaskVO();
	ParentTaskVO parentTaskVO2=new ParentTaskVO();
	
	@Before
	public void setUp() throws Exception {

		taskVO1.setTaskId(1);
		taskVO1.setTaskName("Task 1");
		taskVO1.setStartDate("01/01/2017");
		taskVO1.setEndDate("31/12/2017");
		taskVO1.setPriority(7);
		taskVO1.setTaskStatus("Yes");
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		parentTask.setParentTaskName("Parent Task 1");
		taskVO1.setParentTask(parentTask);
		
		taskVO2.setTaskId(1);
		taskVO2.setTaskName("Task 1");
		taskVO2.setStartDate("01/01/2017");
		taskVO2.setEndDate("31/12/2017");
		taskVO2.setPriority(7);
		taskVO2.setTaskStatus("Yes");
		ParentTask parentTask2 = new ParentTask();
		parentTask2.setParentId(1);
		parentTask2.setParentTaskName("Parent Task 1");
		taskVO2.setParentTask(parentTask2);
		
		parentTaskVO1.setParentId(1);
		parentTaskVO1.setParentTaskName("Parent Task 1");
		parentTaskVO2.setParentId(1);
		parentTaskVO2.setParentTaskName("Parent Task 1");
		
	}
	
	
	private String sampleTestTask = "{\"taskId\": 2,  \"taskName\": \"Test Task 2\", \"startDate\": \"01/03/2017\", \"endDate\": \"31/03/2018\", " 
			+ "\"parentTask\": { \"parentTaskName\": \"Test Parent Task 2\", \"parentId\": 1 }, \"taskStatus\":\"Yes\",  "
			+ "\"priority\": 5}";
	
	@Test
	public final void testGetTaskById() throws Exception {
		Mockito.when(taskService.findTaskById(Mockito.eq(12))).thenReturn(taskVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/task/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//JSONAssert.assertEquals(sampleTestTask, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public final void testAddTask() throws Exception {
		
		Mockito.when(taskService.addTask(Mockito.any(TaskVO.class))).thenReturn(taskVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/addTask")
				.accept(MediaType.APPLICATION_JSON).content(sampleTestTask).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public final void testUpdateTask() throws Exception {
		
		Mockito.when(taskService.updateTask(Mockito.any(TaskVO.class))).thenReturn(taskVO1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/updateTask")
				.accept(MediaType.APPLICATION_JSON).content(sampleTestTask).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public final void testGetAllTasks() throws Exception {
		when(taskService.getTasks()).thenReturn(Arrays.asList(taskVO1, taskVO2));

		mockMvc.perform(get("/api/getTasks")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].taskId", is(1)));

	}
	
	@Test
	public final void testGetAllParentTasks() throws Exception {
		when(taskService.getParentTasks()).thenReturn(Arrays.asList(parentTaskVO1, parentTaskVO2));

		mockMvc.perform(get("/api/getParentTasks")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].parentId", is(1)));

	}

}
