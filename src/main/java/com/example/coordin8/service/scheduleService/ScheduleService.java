package com.example.coordin8.service.scheduleService;

import com.example.coordin8.dto.SlotCountDto;
import com.example.coordin8.entity.ProjectEntity;
import com.example.coordin8.entity.ScheduleDayEntity;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public interface ScheduleService {

    void createScheduleDays(ProjectEntity project);

    void saveAvailableSchedule(Long userId, Long projectId, Long dayId, Long slotId);

    // 자동 생성 확인 및 조회용
    List<ScheduleDayEntity> getScheduleDaysByProject(Long projectId);

    // 가용성 집계 조회용 (Controller 요청 지원)
    List<SlotCountDto> getAvailabilityCountsByProject(Long projectId);
}