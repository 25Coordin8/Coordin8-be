package com.example.coordin8.dto;

import io.swagger.v3.oas.annotations.media.Schema; //
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * AvailableScheduleEntity에서 일자별/슬롯별 가용성 등록 수를 집계한 결과를 담는 DTO.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "일자별/시간대별 가용성 등록 현황 집계 DTO")
public class SlotCountDto {

    // 쿼리 순서와 타입에 맞춰 필드를 정의합니다.

    @Schema(description = "스케줄 일자 ID", example = "1") //
    private Long dayId;     // a.scheduleDay.id (쿼리의 첫 번째 인수)

    @Schema(description = "스케줄 슬롯 ID", example = "5") //
    private Long slotId;    // a.scheduleSlot.id (쿼리의 두 번째 인수)

    @Schema(description = "해당 슬롯에 등록한 사용자 수", example = "3") //
    private Long count;     // COUNT(a) (쿼리의 세 번째 인수, COUNT 결과는 Long 타입)

}