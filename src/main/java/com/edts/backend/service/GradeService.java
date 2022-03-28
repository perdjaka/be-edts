package com.edts.backend.service;

import com.edts.backend.model.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {

	public List<Grade> getAll();

	public Optional<Grade> getByCode(String code);

	public Optional<Grade> getById(long id);

	public Grade saveGrade(Grade grade);

	public void deleteGrade(String code);
}
