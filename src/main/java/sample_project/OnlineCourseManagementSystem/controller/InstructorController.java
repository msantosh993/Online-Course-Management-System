package sample_project.OnlineCourseManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import sample_project.OnlineCourseManagementSystem.dto.InstructorDetails;
import sample_project.OnlineCourseManagementSystem.dto.InstructorDto;
import sample_project.OnlineCourseManagementSystem.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@GetMapping("/getInstructorById/{id}")
	public ResponseEntity<InstructorDetails> getInstructorById(@PathVariable Integer id) {
		InstructorDetails instructor = instructorService.findInstructorById(id);
		return new ResponseEntity<>(instructor, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@GetMapping("/getInstructorByUsername/{username}")
	public ResponseEntity<InstructorDetails> getInstructorByUsername(@PathVariable String username) {
		InstructorDetails instructor = instructorService.findInstructorByUserName(username);
		return new ResponseEntity<>(instructor, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PutMapping("/updateInstructorById/{id}")
	public ResponseEntity<String> updateInstructorById(@PathVariable Integer id,
			@Valid @RequestBody InstructorDto instructorDto) {
		String updatedInstructor = instructorService.updateInstructorById(id, instructorDto);
		return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@DeleteMapping("/deleteInstructorById/{id}")
	public ResponseEntity<String> deleteInstructorById(@PathVariable Integer id) {
		String response = instructorService.deleteInstructor(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
