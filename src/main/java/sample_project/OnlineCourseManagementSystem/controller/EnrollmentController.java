package sample_project.OnlineCourseManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sample_project.OnlineCourseManagementSystem.dto.EnrollmentDto;
import sample_project.OnlineCourseManagementSystem.model.Enrollment;
import sample_project.OnlineCourseManagementSystem.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
@Validated
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PostMapping("/enrollStudent")
	public ResponseEntity<Enrollment> enrollStudent(@Valid @RequestBody EnrollmentDto enrollmentDto) {
		Enrollment enrollment = enrollmentService.enrollment(enrollmentDto);
		return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@GetMapping("/getEnrollment/{enrollmentId}")
	public ResponseEntity<Enrollment> getEnrollment(@PathVariable Integer enrollmentId) {
		Enrollment enrollment = enrollmentService.getEnrollment(enrollmentId);
		return new ResponseEntity<>(enrollment, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_INSTRUCTOR')")
	@GetMapping("/getAllEnrollments")
	public ResponseEntity<List<Enrollment>> getAllEnrollments() {
		List<Enrollment> allEnrollments = enrollmentService.getAllEnrollments();
		return new ResponseEntity<>(allEnrollments, HttpStatus.OK);
	}
}
