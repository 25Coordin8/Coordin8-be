package com.example.coordin8.dto.roleDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleRequestDto {

    @Schema(description = "역할 이름", example = "자료 조사")
    private String task;
}
