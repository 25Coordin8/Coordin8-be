package com.example.coordin8.controller;

import com.example.coordin8.dto.projectDto.ProjectRequestDto;
import com.example.coordin8.dto.projectDto.ProjectResponseDto;
import com.example.coordin8.entity.ProjectEntity;
import com.example.coordin8.service.projectService.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "project", description = "프로젝트 API")
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // ---------------------- CREATE ----------------------
    @PostMapping
    @Operation(summary = "프로젝트 생성", description = "프로젝트 생성 API")
    public ResponseEntity<ProjectResponseDto> createProject(
            @Valid @RequestBody ProjectRequestDto dto) {

        ProjectEntity project = projectService.createProject(dto);
        return ResponseEntity.ok(new ProjectResponseDto(project));
    }

    // ---------------------- READ ALL ----------------------
    @GetMapping
    @Operation(summary = "프로젝트 전체 조회", description = "모든 프로젝트 조회 API")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {

        List<ProjectResponseDto> list = projectService.getAllProjects()
                .stream()
                .map(ProjectResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    // ---------------------- READ ONE ----------------------
    @GetMapping("/{id}")
    @Operation(summary = "프로젝트 조회", description = "특정 프로젝트 조회 API")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Long id) {

        return projectService.getProjectById(id)
                .map(ProjectResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------------------- UPDATE ----------------------
    @PutMapping("/{id}")
    @Operation(summary = "프로젝트 수정", description = "특정 프로젝트 수정 API")
    public ResponseEntity<ProjectResponseDto> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequestDto dto) {

        try {
            ProjectEntity project = projectService.updateProject(id, dto);
            return ResponseEntity.ok(new ProjectResponseDto(project));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ---------------------- DELETE ----------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "프로젝트 삭제", description = "특정 프로젝트 삭제 API")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {

        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
