package com.project.manage.service.projectmanageservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.manage.service.projectmanageservice.dao.ProjectRepository;
import com.project.manage.service.projectmanageservice.dao.UserRepository;
import com.project.manage.service.projectmanageservice.entity.Project;
import com.project.manage.service.projectmanageservice.entity.User;
import com.project.manage.service.projectmanageservice.model.UserVO;
import com.project.manage.service.projectmanageservice.util.ProjectManagementUtility;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProjectRepository projectRepository;
	
	@Transactional(readOnly=false)
	public UserVO addUser(UserVO userVO){
		User user=new User();
		ProjectManagementUtility.copyObjects(userVO, user);
		userRepository.saveAndFlush(user);
		ProjectManagementUtility.copyObjects(user, userVO);
		return userVO;
	}

	@Transactional(readOnly=true)
	@Override
	public UserVO findUserById(int id) {
		User user=userRepository.getOne(id);
		UserVO userVO=new UserVO();
		ProjectManagementUtility.copyObjects(user, userVO);
		return userVO;
	}

	@Transactional(readOnly=true)
	@Override
	public List<UserVO> getUsers() {
		List<User> users=userRepository.findAll();
		List<UserVO> userVOs=new ArrayList<UserVO>();
		for(User user:users){
			UserVO userVO=new UserVO();
			ProjectManagementUtility.copyObjects(user, userVO);
			userVOs.add(userVO);
		}
		return userVOs;
	}

	@Transactional(readOnly=false)
	@Override
	public UserVO updateUser(UserVO userVO) {
		User user=new User();
		ProjectManagementUtility.copyObjects(userVO, user);
		userRepository.saveAndFlush(user);
		ProjectManagementUtility.copyObjects(user, userVO);
		return userVO;
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteUser(int id) {
		List<Project> projects = projectRepository.getProjectsByUserId(id);
		for (Project project : projects) {
			project.setManager(null);
			projectRepository.saveAndFlush(project);
		}
		userRepository.deleteById(id);
	}
}
