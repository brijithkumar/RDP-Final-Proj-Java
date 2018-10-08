package com.project.manage.service.projectmanageservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.project.manage.service.projectmanageservice.dao.UserRepository;
import com.project.manage.service.projectmanageservice.entity.Project;
import com.project.manage.service.projectmanageservice.entity.User;
import com.project.manage.service.projectmanageservice.model.UserVO;
import com.project.manage.service.projectmanageservice.util.ProjectManagementUtility;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTests {
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	ProjectRepository projectRepository;
	
	@Mock
	ProjectManagementUtility projectManagementUtility;
	
	@InjectMocks
	private UserServiceImplementation userServiceImpl;
	
	Project project1 = new Project();
	Project project2 = new Project();
	
	User user2 = new User();
	User user1 = new User();
	UserVO userVO1=new UserVO();

	
	@Before
	public void setUp() throws Exception {
		
		user2.setEmployeeId(12);
		user2.setFirstName("firstName 1");
		user2.setLastName("lastName 1");
		user2.setUserId(1);
		
		user1.setEmployeeId(1);
		user1.setFirstName("firstName 2");
		user1.setLastName("lastName 2");
		user1.setUserId(1);
		
		userVO1.setEmployeeId(1);
		userVO1.setFirstName("firstName 2");
		userVO1.setLastName("lastName 2");
		userVO1.setUserId(1);

	}
	
	@Test
	public final void testAddUser() {
		projectManagementUtility.copyObjects(userVO1, user1);
		Mockito.when(userRepository.saveAndFlush(user1)).thenReturn(user1);
		UserVO userVO = userServiceImpl.addUser(userVO1);
		projectManagementUtility.copyObjects(user1,userVO1);
		assertEquals(1, userVO.getUserId());
	}
	
	@Test
	public final void testGetUsers() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
		List<UserVO> userVOs = userServiceImpl.getUsers();
		assertEquals(1, userVOs.get(0).getUserId());
		assertEquals(2, userVOs.size());
	}

	@Test
	public final void testFindUserById() {
		Mockito.when(userRepository.getOne(Mockito.eq(1))).thenReturn(user1);
		UserVO userVO = userServiceImpl.findUserById(1);
		assertEquals(1, userVO.getUserId());
	}
	
	@Test
	public final void testUpdateUser() {
		projectManagementUtility.copyObjects(userVO1, user1);
		Mockito.when(userRepository.saveAndFlush(user1)).thenReturn(user1);
		UserVO userVO = userServiceImpl.updateUser(userVO1);
		projectManagementUtility.copyObjects(user1,userVO1);
		assertEquals(1, userVO.getUserId());
	}
	
	@Test
	public final void testDeleteUser(){		
		
		Mockito.when(projectRepository.getProjectsByUserId(Mockito.eq(project1.getProjectId()))).thenReturn(Arrays.asList(project1,project2));
		for(Project project:Arrays.asList(project1,project2)){
			project.setManager(null);
			Mockito.when(projectRepository.saveAndFlush(project)).thenReturn(project);
		}
		Mockito.doNothing().when(userRepository).delete(Mockito.any(User.class));
		userServiceImpl.deleteUser(1);
		verify(userRepository, times(1)).deleteById(1);
	}


}
