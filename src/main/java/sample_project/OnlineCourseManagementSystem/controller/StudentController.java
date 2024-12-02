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
import sample_project.OnlineCourseManagementSystem.dto.StudentDetails;
import sample_project.OnlineCourseManagementSystem.dto.StudentDto;
import sample_project.OnlineCourseManagementSystem.service.StudentService;

@RestController
@RequestMapping("api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@GetMapping("/getStudentById/{id}")
	public ResponseEntity<StudentDetails> getStudentById(@PathVariable Integer id) {
		StudentDetails student = studentService.findStudentById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@GetMapping("/getStudentByUsername/{username}")
	public ResponseEntity<StudentDetails> getStudentByUsername(@PathVariable String username) {
		StudentDetails student = studentService.findStudentByUserName(username);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PutMapping("/updateStudentById/{id}")
	public ResponseEntity<String> updateStudentById(@PathVariable Integer id,
			@Valid @RequestBody StudentDto studentDto) {
		String updatedStudent = studentService.updateStudentById(id, studentDto);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@DeleteMapping("/deleteStudentById/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable Integer id) {
		String response = studentService.deleteStudent(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
