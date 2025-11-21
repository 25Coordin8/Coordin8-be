package com.example.coordin8.dto.roleDto;

import com.example.coordin8.entity.RoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleResponseDto {

    @Schema(description = "역할 ID", example = "1")
    private Long id;

    @Schema(description = "역할 이름", example = "발표자료 만들기")
    private String task;

    public RoleResponseDto(RoleEntity entity) {
        this.id = entity.getId();
        this.task = entity.getTask();
    }
}
