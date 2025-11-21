package com.example.coordin8.repository;

import com.example.coordin8.entity.ScheduleSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlotEntity, Long> {
    // 필요하다면 여기에 사용자 정의 쿼리 메서드를 추가할 수 있습니다.
    // 예: List<ScheduleSlotEntity> findByStartTimeBetween(LocalTime start, LocalTime end);
}