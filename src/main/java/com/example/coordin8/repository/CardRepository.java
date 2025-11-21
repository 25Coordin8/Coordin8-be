package com.example.coordin8.repository;

import com.example.coordin8.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    // 기본 CRUD 및 페이징, 정렬 기능 제공
}
