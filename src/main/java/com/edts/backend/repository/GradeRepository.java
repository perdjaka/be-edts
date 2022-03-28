package com.edts.backend.repository;

import com.edts.backend.model.Grade;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
	Optional<Grade> findByCode(String code);
}
