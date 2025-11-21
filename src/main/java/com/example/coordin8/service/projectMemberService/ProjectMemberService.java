package com.example.coordin8.service.projectMemberService;

import com.example.coordin8.dto.projectmemberDto.ProjectMemberRequestDto;
import com.example.coordin8.entity.ProjectMemberEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberService {

    // ---------------------- CREATE ----------------------
    ProjectMemberEntity createProjectMember(ProjectMemberRequestDto requestDto);

    // ---------------------- READ ALL ----------------------
    List<ProjectMemberEntity> getAllProjectMembers();

    // ---------------------- READ ONE ----------------------
    Optional<ProjectMemberEntity> getProjectMemberById(Long id);

    // ---------------------- UPDATE ----------------------
    ProjectMemberEntity updateProjectMember(Long id, ProjectMemberRequestDto requestDto);

    // ---------------------- DELETE ----------------------
    void deleteProjectMember(Long id);
}
