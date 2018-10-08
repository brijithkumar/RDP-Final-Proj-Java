package com.project.manage.service.projectmanageservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.manage.service.projectmanageservice.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query("SELECT p FROM Project p WHERE p.manager.userId = :id")
	public List<Project> getProjectsByUserId(@Param("id") Integer id);

}
