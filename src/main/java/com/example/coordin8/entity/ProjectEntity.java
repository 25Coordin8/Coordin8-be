package com.example.coordin8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(
        name = "project",
        // 'code' 필드에 UNIQUE 제약 조건을 적용하기 위해 @UniqueConstraint 추가
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code"})
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String projectName;

    @Column(name = "goal", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String projectGoal;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(
            name = "code",
            columnDefinition = "CHAR(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci",
            nullable = false
    )
    private String code;


}
