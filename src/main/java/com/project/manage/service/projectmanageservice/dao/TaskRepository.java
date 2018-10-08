package com.project.manage.service.projectmanageservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.manage.service.projectmanageservice.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	

}
