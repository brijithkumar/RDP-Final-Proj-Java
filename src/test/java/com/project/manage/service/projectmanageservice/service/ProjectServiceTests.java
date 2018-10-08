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

import com.project.manage.service.projectmanageservice.dao.ProjectRepository;
import com.project.manage.service.projectmanageservice.entity.Project;
import com.project.manage.service.projectmanageservice.entity.User;
import com.project.manage.service.projectmanageservice.model.ProjectVO;
import com.project.manage.service.projectmanageservice.util.ProjectManagementUtility;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectServiceTests {
	
	@Mock
	ProjectRepository projectRepository;
	
	@Mock
	ProjectManagementUtility projectManagementUtility;
	
	@InjectMocks
	private ProjectServiceImplementation projectServiceImpl;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Project project1 = new Project();
	Project project2 = new Project();
	Project project3 =new Project();
	ProjectVO projectVO1=new ProjectVO();
	ProjectVO projectVO2=new ProjectVO();
	
	User user = new User();

	
	@Before
	public void setUp() throws Exception {
		
		user.setEmployeeId(12);
		user.setFirstName("firstName 1");
		user.setLastName("lastName 1");
		user.setUserId(1);

		project1.setProjectId(1);
		project1.setProjectName("Project 1");
		project1.setStartDate(dateFormat.parse("2017-01-01"));
		project1.setEndDate(dateFormat.parse("2017-12-01"));
		project1.setPriority(5);
		project1.setCompletedStatus("Yes");
		project1.setManager(user);

		project2 = new Project();
		project2.setProjectId(3);
		project2.setProjectName("Project 2");
		project1.setStartDate(dateFormat.parse("2017-01-01"));
		project1.setEndDate(dateFormat.parse("2018-12-01"));
		project2.setPriority(7);
		project2.setCompletedStatus("Yes");
		project2.setManager(user);
		
		project3.setProjectId(3);
		project3.setProjectName("Project 3");
		project1.setStartDate(dateFormat.parse("2018-01-01"));
		project1.setEndDate(dateFormat.parse("2018-12-01"));
		project3.setPriority(7);
		project3.setCompletedStatus("Yes");
		project3.setManager(user);
		
		projectVO1.setProjectId(1);
		projectVO1.setProjectName("Project 1");
		projectVO1.setStartDate("2017-01-01");
		projectVO1.setEndDate("2017-12-01");
		projectVO1.setPriority(7);
		projectVO1.setCompletedStatus("Yes");
		projectVO1.setManager(user);
	}
	
	@Test
	public final void testAddProject() {
		projectManagementUtility.copyObjects(projectVO1, project3);
		Mockito.when(projectRepository.saveAndFlush(project3)).thenReturn(project3);
		ProjectVO projectVO = projectServiceImpl.addProject(projectVO1);
		projectVO1.setProjectId(project3.getProjectId());
		projectVO1.setProjectName(project3.getProjectName());
		projectVO1.setStartDate(projectManagementUtility.convertDateToString(project3.getStartDate()));
		projectVO1.setEndDate(projectManagementUtility.convertDateToString(project3.getEndDate()));
		projectVO1.setPriority(project3.getPriority());
		projectVO1.setCompletedStatus(project3.getCompletedStatus());
		projectVO1.setManager(project3.getManager());
		assertEquals(1, projectVO.getProjectId());
		assertEquals("Project 1", projectVO.getProjectName());
	}
	
	@Test
	public final void testGetProjects() {
		when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));
		List<ProjectVO> projectVOs = projectServiceImpl.getProjects();
		assertEquals(1, projectVOs.get(0).getProjectId());
		assertEquals(2, projectVOs.size());
	}

	@Test
	public final void testFindProjectById() {
		Mockito.when(projectRepository.getOne(Mockito.eq(1))).thenReturn(project1);
		ProjectVO projectVO = projectServiceImpl.findProjectById(1);
		assertEquals(1, projectVO.getProjectId());
		assertEquals("Project 1", projectVO.getProjectName());
	}
	
	@Test
	public final void testUpdateProject() {
		projectManagementUtility.copyObjects(projectVO1, project3);
		Mockito.when(projectRepository.saveAndFlush(project3)).thenReturn(project3);
		ProjectVO projectVO = projectServiceImpl.updateProject(projectVO1);
		projectManagementUtility.copyObjects(project3,projectVO);
		assertEquals(1, projectVO.getProjectId());
		assertEquals("Project 1", projectVO.getProjectName());
	}


}
