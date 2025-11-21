package com.example.coordin8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_member")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 추가 권장
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false) // ⬅️ nullable = false 추가
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 추가 권장
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false) // ⬅️ nullable = false 추가
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 추가 권장
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false) // ⬅️ nullable = false 추가
    private RoleEntity role;
}
