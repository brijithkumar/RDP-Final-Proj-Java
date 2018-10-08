package com.project.manage.service.projectmanageservice.service;

import java.util.List;

import javax.validation.Valid;

import com.project.manage.service.projectmanageservice.model.ProjectVO;

public interface ProjectService {

	ProjectVO findProjectById(int id);

	ProjectVO addProject(ProjectVO projectVO);

	List<ProjectVO> getProjects();

	@Valid
	ProjectVO updateProject(@Valid ProjectVO projectVO);

}
