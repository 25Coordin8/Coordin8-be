package com.example.coordin8.dto.focussessionDto;

import com.example.coordin8.entity.SessionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FocusSessionRequestDto {

    @Schema(description = "프로젝트 ID", example = "1")
    private Long projectId;

    @Schema(description = "사용자 ID", example = "5")
    private Long userId;

    @Schema(description = "세션 타입 (Focus / Break)", example = "FOCUS")
    private SessionType sessionType;

    @Schema(description = "시작 시간", example = "2025-01-20T14:30:00")
    private LocalDateTime startTime;

    @Schema(description = "종료 시간 (처음 생성 시 null 허용)", example = "2025-01-20T15:00:00")
    private LocalDateTime endTime;
}
