package com.example.coordin8.dto.projectDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectRequestDto {

    @Schema(description = "프로젝트 id", example = "1")
    private Long id;

    @Schema(description = "프로젝트 제목", example = "교내톤")
    private String projectName;

    @Schema(description = "프로젝트 내용", example = "2025.11.22. 예정되어있는 국민대 멋사 행사입니다.")
    private String projectGoal;

    @Schema(description = "프로젝트 생성일", example = "2025-11-21")
    private LocalDate deadline;

    @Schema(description = "프로젝트 코드", example = "ABC123")
    private String code;
}
