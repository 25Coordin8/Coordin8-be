package com.example.coordin8.repository;

import com.example.coordin8.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    boolean existsByCode(String code);
}
