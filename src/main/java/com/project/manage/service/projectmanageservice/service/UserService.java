package com.project.manage.service.projectmanageservice.service;

import java.util.List;

import com.project.manage.service.projectmanageservice.model.UserVO;

public interface UserService {
	
	UserVO addUser(UserVO userVO);

	UserVO findUserById(int id);

	List<UserVO> getUsers();

	UserVO updateUser(UserVO userVO);

	void deleteUser(int id);

}
