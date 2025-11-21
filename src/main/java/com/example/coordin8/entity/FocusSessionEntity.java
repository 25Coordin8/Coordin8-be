package com.example.coordin8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "focussession")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FocusSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING) // ENUM 타입으로 저장 ('Focus' 또는 'Break'만 허용)
    @Column(name = "session_type", length = 50, nullable = false)
    private SessionType sessionType;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time") // 세션 진행 중일 수 있으므로 nullable
    private LocalDateTime endTime;

    @Column(name = "duration_seconds") // 누적 시간 (초)
    private Integer durationSeconds;


}
