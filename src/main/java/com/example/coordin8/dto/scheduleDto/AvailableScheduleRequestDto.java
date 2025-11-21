package com.example.coordin8.dto.scheduleDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data; // @Getter, @Setter, @ToString 등을 포함
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 사용자가 특정 시간 슬롯에 대해 가용성을 등록할 때 사용되는 요청 DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "가용성 등록 요청 DTO")
public class AvailableScheduleRequestDto {

    @Schema(description = "가용성을 등록하는 사용자 ID", example = "101")
    private Long userId;

    @Schema(description = "가용성을 등록할 프로젝트 ID", example = "2")
    private Long projectId;

    @Schema(description = "가용성을 등록할 스케줄 일자 ID", example = "50")
    private Long dayId;

    @Schema(description = "가용성을 등록할 스케줄 슬롯 ID", example = "15")
    private Long slotId;
}