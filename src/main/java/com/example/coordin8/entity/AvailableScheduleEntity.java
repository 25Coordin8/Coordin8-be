package com.example.coordin8.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // 추가: 기본 생성자
import lombok.AllArgsConstructor; // 추가: 전체 필드 생성자
import lombok.Builder; // 추가: 빌더 패턴

@Entity
@Table(
        name = "available_schedule",
        uniqueConstraints = @UniqueConstraint(
                name = "uc_user_project_day_slot", // 유니크 제약 조건 이름 명시
                columnNames = {"user_id", "project_id", "schedule_day_id", "schedule_slot_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor // JPA 사용을 위해 필수
@AllArgsConstructor // 생성자 편의성 제공
@Builder // 깔끔한 객체 생성을 위해 제공
public class AvailableScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. 성능을 위해 fetch = FetchType.LAZY 추가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // 2. 필수 필드이므로 nullable=false 추가
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false) // 2. 필수 필드이므로 nullable=false 추가
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_day_id", nullable = false) // 2. 필수 필드이므로 nullable=false 추가
    private ScheduleDayEntity scheduleDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_slot_id", nullable = false) // 2. 필수 필드이므로 nullable=false 추가
    private ScheduleSlotEntity scheduleSlot;

    @Column(nullable = false)
    private Boolean available = true;
}