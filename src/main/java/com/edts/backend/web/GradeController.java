package com.edts.backend.web;

import com.edts.backend.model.Grade;
import com.edts.backend.service.GradeService;
import com.edts.backend.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeController {
	@Autowired
	private GradeService gradeService;

	private static final String ENTITY_NAME = "Grade";

	@GetMapping(value = "/grade")
	public ResponseEntity<List<Grade>> getAllGrades() {
		List<Grade> grades = this.gradeService.getAll();
		return new ResponseEntity<>(grades, HttpStatus.OK);
	}

	@PostMapping(value = "/grade/add")
	public ResponseEntity<Grade> addGrade(@RequestBody Grade grade) throws URISyntaxException {
		Grade result = this.gradeService.saveGrade(grade);
		return ResponseEntity.created(new URI("/grade/add/" + result.getId()))
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, String.valueOf(grade.getId()))).body(result);
	}

	@PutMapping(value = "/grade/update/{code}")
	public ResponseEntity<Grade> updateGrade(@PathVariable("code") String code, @RequestBody Grade grade) {
		Grade temp = this.gradeService.getByCode(code)
				.orElseThrow(() -> new RuntimeException("Not found Grade with code = " + code));
		temp.setName(grade.getName());
		temp.setRate(grade.getRate());
		Grade result = this.gradeService.saveGrade(temp);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, String.valueOf(grade.getId()))).body(result);

	}

	@DeleteMapping(value = "/grade/delete/{code}")
	public ResponseEntity<Void> deleteGrade(@PathVariable("code") String code) {
		this.gradeService.getByCode(code)
				.orElseThrow(() -> new RuntimeException("Not found Grade with code = " + code));
		this.gradeService.deleteGrade(code);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, code)).build();
	}
}
