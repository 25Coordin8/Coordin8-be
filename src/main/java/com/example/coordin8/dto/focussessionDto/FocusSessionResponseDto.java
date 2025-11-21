package com.example.coordin8.dto.focussessionDto;

import com.example.coordin8.entity.SessionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FocusSessionResponseDto {

    @Schema(description = "세션 고유 ID", example = "10")
    private Long id;

    @Schema(description = "프로젝트 ID", example = "1")
    private Long projectId;

    @Schema(description = "사용자 ID", example = "5")
    private Long userId;

    @Schema(description = "세션 타입", example = "FOCUS")
    private SessionType sessionType;

    @Schema(description = "시작 시간", example = "2025-01-20T14:30:00")
    private LocalDateTime startTime;

    @Schema(description = "종료 시간", example = "2025-01-20T15:00:00")
    private LocalDateTime endTime;

    @Schema(description = "총 집중 시간 (초)", example = "1800")
    private Integer durationSeconds;
}
