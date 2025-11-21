package com.example.coordin8.dto.userDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDto {

    @Schema(description = "사용자 id", example = "1")
    private Long id;

    @Schema(description = "사용자 name", example = "강다은")
    private String userName;

    @Schema(description = "사용자 major", example = "정보보안암호수학과")
    private String userMajor;

    @Schema(description = "사용자 bio", example = "보안에 관심이 많은 대학생")
    private String bio;

    @Schema(description = "사용자 avatar id", example = "1")
    private Long avatarId;
}
