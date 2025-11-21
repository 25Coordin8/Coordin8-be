package com.example.coordin8.service.projectService;

import com.example.coordin8.dto.projectDto.ProjectRequestDto;
import com.example.coordin8.dto.projectDto.ProjectResponseDto;
import com.example.coordin8.entity.ProjectEntity;
import com.example.coordin8.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // ---------------------- CREATE ----------------------
    @Override
    public ProjectEntity createProject(ProjectRequestDto dto) {

        // 프로젝트 코드 중복 체크
        if (projectRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Project code already exists!");
        }

        ProjectEntity project = ProjectEntity.builder()
                .projectName(dto.getProjectName())
                .projectGoal(dto.getProjectGoal())
                .deadline(dto.getDeadline())
                .code(dto.getCode())
                .build();

        return projectRepository.save(project);
    }


    // ---------------------- READ ALL ----------------------
    @Override
    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }


    // ---------------------- READ ONE ----------------------
    @Override
    public Optional<ProjectEntity> getProjectById(Long id) {
        return projectRepository.findById(id);
    }


    // ---------------------- UPDATE ----------------------
    @Override
    public ProjectEntity updateProject(Long id, ProjectRequestDto dto) {

        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Code가 변경될 경우 중복 체크
        if (!project.getCode().equals(dto.getCode())
                && projectRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Project code already exists!");
        }

        project.setProjectName(dto.getProjectName());
        project.setProjectGoal(dto.getProjectGoal());
        project.setDeadline(dto.getDeadline());
        project.setCode(dto.getCode());

        return projectRepository.save(project);
    }


    // ---------------------- DELETE ----------------------
    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
