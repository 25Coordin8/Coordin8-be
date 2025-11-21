package com.example.coordin8.service.projectService;

import com.example.coordin8.dto.projectDto.ProjectRequestDto;
import com.example.coordin8.entity.ProjectEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    ProjectEntity createProject(ProjectRequestDto requestDto);

    ProjectEntity updateProject(Long Id, ProjectRequestDto requestDto);

    List<ProjectEntity> getAllProjects();

    Optional<ProjectEntity> getProjectById(Long Id);

    void deleteProject(Long Id);
}
