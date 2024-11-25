package sample_project.OnlineCourseManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sample_project.OnlineCourseManagementSystem.dto.InstructorDto;
import sample_project.OnlineCourseManagementSystem.model.Instructor;
import sample_project.OnlineCourseManagementSystem.service.InstructorManagementService;

@RestController
@RequestMapping("/api/instructors")
@Validated
public class InstructorController {

	@Autowired
	private InstructorManagementService instructorManagementService;

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PostMapping("/createInstructor")
	public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody InstructorDto instructorDto) {
		Instructor instructor = instructorManagementService.createInstructor(instructorDto);
		return new ResponseEntity<>(instructor, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@GetMapping("/getAllInstructors")
	public ResponseEntity<List<Instructor>> getAllInstructors() {
		List<Instructor> instructors = instructorManagementService.getAllInstructors();
		return new ResponseEntity<>(instructors, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@GetMapping("/getInstructorById/{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable Integer id) {
		Instructor instructor = instructorManagementService.getInstructorById(id);
		return new ResponseEntity<>(instructor, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PutMapping("/updateInstructor/{id}")
	public ResponseEntity<Instructor> updateInstructor(@Valid @PathVariable Integer id,
			@RequestBody InstructorDto instructorDto) {
		Instructor updatedInstructor = instructorManagementService.updateInstructor(id, instructorDto);
		return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@DeleteMapping("/deleteInstructor/{id}")
	public ResponseEntity<String> deleteInstructor(@PathVariable Integer id) {
		String response = instructorManagementService.deleteInstructor(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
