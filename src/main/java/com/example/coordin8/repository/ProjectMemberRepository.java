package com.example.coordin8.repository;

import com.example.coordin8.entity.ProjectMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMemberEntity, Long> {
}
