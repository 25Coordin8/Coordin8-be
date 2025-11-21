package com.example.coordin8.dto.projectDto;

import com.example.coordin8.entity.ProjectEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectResponseDto {

    private Long id;
    private String projectName;
    private String projectGoal;
    private LocalDate deadline;
    private String code;

    public ProjectResponseDto(ProjectEntity projectEntity) {
        this.id = projectEntity.getId();
        this.projectName = projectEntity.getProjectName();
        this.projectGoal = projectEntity.getProjectGoal();
        this.deadline = projectEntity.getDeadline();
        this.code = projectEntity.getCode();
    }
}
