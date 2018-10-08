package com.project.manage.service.projectmanageservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.manage.service.projectmanageservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
