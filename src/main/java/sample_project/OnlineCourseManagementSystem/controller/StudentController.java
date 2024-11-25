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
import sample_project.OnlineCourseManagementSystem.dto.StudentDto;
import sample_project.OnlineCourseManagementSystem.model.Student;
import sample_project.OnlineCourseManagementSystem.service.StudentService;

@RestController
@RequestMapping("/api/students")
@Validated
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PostMapping("/createStudent")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentDto studentDto) {
		Student student = studentService.createStudent(studentDto);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@GetMapping("/getStudentById/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer studentId) {
		Student student = studentService.getStudentById(studentId);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@GetMapping("/getStudentByName/{studentName}")
	public ResponseEntity<Student> getStudentById(@PathVariable String studentName) {
		Student student = studentService.getStudentByName(studentName);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PutMapping("/updateStudent/{studentId}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer studentId,
			@Valid @RequestBody StudentDto studentDto) {
		Student student = studentService.updateStudent(studentId, studentDto);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@GetMapping("/getAllStudents")
	public ResponseEntity<List<Student>> findAllStudents() {
		List<Student> allStudents = studentService.findAllStudents();
		return new ResponseEntity<>(allStudents, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@DeleteMapping("/deleteStudent/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId) {
		String response = studentService.deleteStudent(studentId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
