package com.project.manage.service.projectmanageservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.manage.service.projectmanageservice.dao.ParentTaskRepository;
import com.project.manage.service.projectmanageservice.dao.TaskRepository;
import com.project.manage.service.projectmanageservice.entity.ParentTask;
import com.project.manage.service.projectmanageservice.entity.Project;
import com.project.manage.service.projectmanageservice.entity.Task;
import com.project.manage.service.projectmanageservice.entity.User;
import com.project.manage.service.projectmanageservice.model.ParentTaskVO;
import com.project.manage.service.projectmanageservice.model.ProjectVO;
import com.project.manage.service.projectmanageservice.model.TaskVO;
import com.project.manage.service.projectmanageservice.util.ProjectManagementUtility;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTests {
	
	@Mock
	TaskRepository taskRepository;
	@Mock
	ParentTaskRepository parentTaskRepository;
	
	@Mock
	ProjectManagementUtility projectManagementUtility;
	
	@InjectMocks
	private TaskServiceImplementation taskServiceImpl;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	Project project1 = new Project();
	Task task1 = new Task();
	Task task2 = new Task();
	ParentTask parentTask3 = new ParentTask();
	ParentTask parentTask4 = new ParentTask();
	TaskVO taskVO1=new TaskVO();
	
	User user = new User();

	
	@Before
	public void setUp() throws Exception {
		
		user.setEmployeeId(12);
		user.setFirstName("firstName 1");
		user.setLastName("lastName 1");
		user.setUserId(1);
		
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
		
		task1.setTaskId(1);
		task1.setTaskName("Task 1");
		task1.setStartDate("01/01/2017");
		task1.setEndDate("31/12/2017");
		task1.setPriority(7);
		task1.setTaskStatus("Yes");
		ParentTask parentTask2 = new ParentTask();
		parentTask2.setParentId(1);
		parentTask2.setParentTaskName("Parent Task 1");
		task1.setParentTask(parentTask2);
		
		task2.setTaskId(1);
		task2.setTaskName("Task 2");
		task2.setStartDate("01/01/2018");
		task2.setEndDate("31/12/2018");
		task2.setPriority(7);
		task2.setTaskStatus("Yes");
		ParentTask parentTask3 = new ParentTask();
		parentTask3.setParentId(1);
		parentTask3.setParentTaskName("Parent Task 2");
		task2.setParentTask(parentTask2);
		
		parentTask3.setParentId(1);
		parentTask3.setParentTaskName("Parent Task 1");
		parentTask4.setParentId(5);
		parentTask4.setParentTaskName("Parent Task 5");
	}
	
	@Test
	public final void testAddProject() {
		projectManagementUtility.copyObjects(taskVO1, task1);
		taskVO1.setParentTaskSelectionModel(true);
		Mockito.when(parentTaskRepository.save(task1.getParentTask())).thenReturn(parentTask3);
		Mockito.when(taskRepository.saveAndFlush(task1)).thenReturn(task1);
		TaskVO taskVO = taskServiceImpl.addTask(taskVO1);
		projectManagementUtility.copyObjects(task1,taskVO);
		assertEquals(1, taskVO.getTaskId());
		assertEquals("Task 1", taskVO.getTaskName());
	}
	
	@Test
	public final void testGetTasks() {
		when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));
		List<TaskVO> taskVOs = taskServiceImpl.getTasks();
		assertEquals(1, taskVOs.get(0).getTaskId());
		assertEquals(2, taskVOs.size());
	}

	@Test
	public final void testFindTaskById() {
		Mockito.when(taskRepository.getOne(Mockito.eq(1))).thenReturn(task1);
		TaskVO taskVO = taskServiceImpl.findTaskById(1);
		assertEquals(1, taskVO.getTaskId());
		assertEquals("Task 1", taskVO.getTaskName());
	}
	
	@Test
	public final void testUpdateTask() {
		projectManagementUtility.copyObjects(taskVO1, task1);
		Mockito.when(taskRepository.saveAndFlush(task1)).thenReturn(task1);
		TaskVO taskVO = taskServiceImpl.updateTask(taskVO1);
		projectManagementUtility.copyObjects(task1,taskVO1);
		assertEquals(1, taskVO.getTaskId());
		assertEquals("Task 1", taskVO.getTaskName());
	}
	
	@Test
	public final void testGetParentTasks() {
		when(parentTaskRepository.findAll()).thenReturn(Arrays.asList(parentTask3, parentTask4));
		List<ParentTaskVO> parentTaskVOs = taskServiceImpl.getParentTasks();
		assertEquals(2, parentTaskVOs.size());
	}


}
