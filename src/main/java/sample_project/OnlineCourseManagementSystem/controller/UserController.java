package sample_project.OnlineCourseManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sample_project.OnlineCourseManagementSystem.dto.InstructorDto;
import sample_project.OnlineCourseManagementSystem.dto.StudentDto;
import sample_project.OnlineCourseManagementSystem.dto.UserDto;
import sample_project.OnlineCourseManagementSystem.service.UserService;

@RequestMapping(value = "/api")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register/instructor")
	public ResponseEntity<String> registerInstructor(@Valid @RequestBody InstructorDto instructorDto) {
		String user = userService.registerAsInstructor(instructorDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PostMapping("/register/student")
	public ResponseEntity<String> registerStudent(@Valid @RequestBody StudentDto studentDto) {
		String user = userService.registerAsStudent(studentDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody UserDto userDto) {
		String jwtToken = userService.verify(userDto);
		return new ResponseEntity<>(jwtToken, HttpStatus.OK);
	}
}
