package com.example.coordin8.controller;

import com.example.coordin8.dto.SlotCountDto;
import com.example.coordin8.dto.scheduleDto.AvailableScheduleRequestDto;
import com.example.coordin8.entity.ScheduleDayEntity;
import com.example.coordin8.service.scheduleService.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "schedule", description = "스케줄 및 가용성 API")
@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // ----------------------------------------------------
    // C R E A T E (가용성 등록)
    // ----------------------------------------------------
    @Operation(summary = "특정 시간 슬롯 가용성 등록",
            description = "사용자가 특정 프로젝트의 특정 일자/슬롯에 대해 가용성을 등록합니다.")
    @PostMapping("/availability")
    public ResponseEntity<Void> saveAvailableSchedule(@RequestBody AvailableScheduleRequestDto requestDto) {

        scheduleService.saveAvailableSchedule(
                requestDto.getUserId(),
                requestDto.getProjectId(),
                requestDto.getDayId(),
                requestDto.getSlotId()
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ----------------------------------------------------
    // R E A D (자동 생성 확인 및 집계)
    // ----------------------------------------------------
    @Operation(summary = "프로젝트별 스케줄 일자 조회",
            description = "자동 생성된 7일치 일자(ScheduleDay)를 포함하여 특정 프로젝트의 모든 일자를 조회합니다.")
    @GetMapping("/days/project/{projectId}")
    public List<ScheduleDayEntity> getScheduleDays(@PathVariable Long projectId) {
        return scheduleService.getScheduleDaysByProject(projectId);
    }

    @Operation(summary = "프로젝트별 가용성 등록 현황 집계",
            description = "특정 프로젝트의 일자/슬롯별로 가용성을 등록한 사용자 수를 조회합니다.")
    @GetMapping("/availability/project/{projectId}/counts")
    public List<SlotCountDto> getAvailabilityCounts(@PathVariable Long projectId) {
        return scheduleService.getAvailabilityCountsByProject(projectId);
    }
}