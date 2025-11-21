package com.example.coordin8.dto.projectmemberDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProjectMemberRequestDto {
    @Schema(description = "프로젝트 멤버 ID", example = "1")
    private Long id;

    @Schema(description = "연관된 프로젝트 ID", example = "1")
    private Long projectId;

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "사용자 역할 ID", example = "1")
    private Long roleId;
}

