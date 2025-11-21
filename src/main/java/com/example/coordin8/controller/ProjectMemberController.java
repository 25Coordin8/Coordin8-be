package com.example.coordin8.controller;

import com.example.coordin8.dto.projectmemberDto.*;
import com.example.coordin8.entity.ProjectMemberEntity;
import com.example.coordin8.service.projectMemberService.ProjectMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "project_member", description = "프로젝트 멤버 API")
@RestController
@RequestMapping("/api/v1/project-members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @PostMapping
    @Operation(summary = "프로젝트 멤버 생성", description = "프로젝트에 멤버 추가")
    public ResponseEntity<ProjectMemberResponseDto> createProjectMember(
            @Valid @RequestBody ProjectMemberRequestDto requestDto) {

        ProjectMemberEntity entity = projectMemberService.createProjectMember(requestDto);
        return ResponseEntity.ok(new ProjectMemberResponseDto(entity));
    }

    @GetMapping
    @Operation(summary = "모든 프로젝트 멤버 조회", description = "프로젝트 멤버 전체 조회")
    public ResponseEntity<List<ProjectMemberResponseDto>> getAllProjectMembers() {
        List<ProjectMemberResponseDto> list = projectMemberService.getAllProjectMembers()
                .stream()
                .map(ProjectMemberResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "프로젝트 멤버 조회", description = "ID로 특정 프로젝트 멤버 조회")
    public ResponseEntity<ProjectMemberResponseDto> getProjectMember(@PathVariable Long id) {
        return projectMemberService.getProjectMemberById(id)
                .map(ProjectMemberResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "프로젝트 멤버 수정", description = "ID로 프로젝트 멤버 정보 수정")
    public ResponseEntity<ProjectMemberResponseDto> updateProjectMember(
            @PathVariable Long id,
            @Valid @RequestBody ProjectMemberRequestDto requestDto) {

        try {
            ProjectMemberEntity updated = projectMemberService.updateProjectMember(id, requestDto);
            return ResponseEntity.ok(new ProjectMemberResponseDto(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "프로젝트 멤버 삭제", description = "ID로 프로젝트 멤버 삭제")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long id) {
        projectMemberService.deleteProjectMember(id);
        return ResponseEntity.noContent().build();
    }
}
