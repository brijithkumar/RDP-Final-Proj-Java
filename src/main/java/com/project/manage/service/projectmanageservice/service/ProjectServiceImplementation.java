package com.project.manage.service.projectmanageservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.manage.service.projectmanageservice.dao.ProjectRepository;
import com.project.manage.service.projectmanageservice.entity.Project;
import com.project.manage.service.projectmanageservice.model.ProjectVO;
import com.project.manage.service.projectmanageservice.util.ProjectManagementUtility;

@Service
public class ProjectServiceImplementation implements ProjectService{
	
	@Autowired
	ProjectRepository projectRepository;

	@Transactional(readOnly=true)
	@Override
	public ProjectVO findProjectById(int id) {
		Project project=projectRepository.getOne(id);
		ProjectVO projectVO=new ProjectVO();
		ProjectManagementUtility.copyObjects(project, projectVO);
		return projectVO;
	}

	@Transactional(readOnly=false)
	@Override
	public ProjectVO addProject(ProjectVO projectVO) {
		Project project=new Project();
		ProjectManagementUtility.copyObjects(projectVO, project);
		projectRepository.saveAndFlush(project);
		ProjectManagementUtility.copyObjects(project, projectVO);
		return projectVO;
	}

	@Transactional(readOnly=true)
	@Override
	public List<ProjectVO> getProjects() {
		List<Project> projects=projectRepository.findAll();
		List<ProjectVO> projectVOs=new ArrayList<ProjectVO>();
		for(Project project:projects){
			ProjectVO projectVO=new ProjectVO();
			ProjectManagementUtility.copyObjects(project, projectVO);
			projectVOs.add(projectVO);
		}
		return projectVOs;
	}

	@Transactional(readOnly=false)
	@Override
	public ProjectVO updateProject(ProjectVO projectVO) {
		Project project=new Project();
		ProjectManagementUtility.copyObjects(projectVO, project);
		projectRepository.saveAndFlush(project);
		ProjectManagementUtility.copyObjects(project, projectVO);
		return projectVO;
	}

}
