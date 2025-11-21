package com.example.coordin8.repository;

import com.example.coordin8.entity.ScheduleDayEntity;
import com.example.coordin8.entity.ProjectEntity; // ProjectEntity 임포트 필요
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // List 임포트 필요

@Repository
public interface ScheduleDayRepository extends JpaRepository<ScheduleDayEntity, Long> {

    List<ScheduleDayEntity> findByProject(ProjectEntity project);

}