package com.example.coordin8.dto.projectmemberDto;

import com.example.coordin8.entity.ProjectMemberEntity;
import lombok.Data;

@Data
public class ProjectMemberResponseDto {

    private Long id;
    private Long projectId;
    private Long userId;
    private Long roleId;

    public ProjectMemberResponseDto(ProjectMemberEntity projectMemberEntity) {
        this.id = projectMemberEntity.getId(); // 엔티티의 id로 초기화
        this.projectId = projectMemberEntity.getProject() != null ? projectMemberEntity.getProject().getId() : null;
        this.userId = projectMemberEntity.getUser() != null ? projectMemberEntity.getUser().getId() : null;
        this.roleId = projectMemberEntity.getRole() != null ? projectMemberEntity.getRole().getId() : null;
    }
}
