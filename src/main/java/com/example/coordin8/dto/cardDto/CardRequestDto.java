package com.example.coordin8.dto.cardDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CardRequestDto {

    @Schema(description = "카드 제목", example = "첫 번째 카드")
    private String cardTitle;

    @Schema(description = "카드 내용", example = "이번 주 회의 내용 정리")
    private String cardContent;

    @Schema(description = "카드 작성일", example = "2025-11-21T15:30:00")
    private LocalDateTime cardDate;

    @Schema(description = "연관된 프로젝트 ID", example = "1")
    private Long projectId;

    @Schema(description = "작성자 사용자 ID", example = "1")
    private Long userId;
}
