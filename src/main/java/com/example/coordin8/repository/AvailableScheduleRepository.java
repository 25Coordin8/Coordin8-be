package com.example.coordin8.repository;

import com.example.coordin8.entity.AvailableScheduleEntity;
import com.example.coordin8.entity.UserEntity; //
import com.example.coordin8.entity.ProjectEntity; //
import com.example.coordin8.entity.ScheduleDayEntity; //
import com.example.coordin8.entity.ScheduleSlotEntity; //
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.example.coordin8.dto.SlotCountDto;

@Repository
public interface AvailableScheduleRepository extends JpaRepository<AvailableScheduleEntity, Long> {

    @Query("""
        SELECT new com.example.coordin8.dto.SlotCountDto(
            a.scheduleDay.id,
            a.scheduleSlot.id,
            COUNT(a)
        )
        FROM AvailableScheduleEntity a
        WHERE a.project.id = :projectId
        GROUP BY a.scheduleDay.id, a.scheduleSlot.id
    """)
    List<SlotCountDto> findAvailabilityCounts(Long projectId);

    /**
     * 주어진 네 가지 엔티티 조합으로 AvailableSchedule 엔티티가 이미 존재하는지 확인합니다.
     */
    boolean existsByUserAndProjectAndScheduleDayAndScheduleSlot(
            UserEntity user,
            ProjectEntity project,
            ScheduleDayEntity day,
            ScheduleSlotEntity slot // 이 4개의 필드가 AvailableScheduleEntity에 있어야 합니다.
    );
}