package com.edts.backend.service.impl;

import com.edts.backend.model.Grade;
import com.edts.backend.repository.GradeRepository;
import com.edts.backend.service.GradeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {
	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public List<Grade> getAll() {
		return this.gradeRepository.findAll();
	}

	@Override
	public Optional<Grade> getByCode(String code) {
		return this.gradeRepository.findByCode(code);
	}

	@Override
	public Optional<Grade> getById(long id) {
		return this.gradeRepository.findById(id);
	}

	@Override
	public Grade saveGrade(Grade grade) {
		Optional<Grade> temp = this.gradeRepository.findByCode(grade.getCode());
		if (!temp.isPresent()) {
			return this.gradeRepository.save(grade);
		} else {
			Grade updatedTempGrade = temp.get();
			Grade updatedGrade = new Grade(updatedTempGrade.getId(), grade.getCode(), grade.getName(), grade.getRate());
			return this.gradeRepository.save(updatedGrade);
		}

	}

	@Override
	public void deleteGrade(String code) {
		Grade deletedGrade = this.gradeRepository.findByCode(code).get();
		this.gradeRepository.delete(deletedGrade);
	}

}
