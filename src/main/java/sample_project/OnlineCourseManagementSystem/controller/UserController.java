package sample_project.OnlineCourseManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sample_project.OnlineCourseManagementSystem.dto.UserDto;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.service.userService;

@RestController
public class UserController {
	@Autowired
	private userService userService;

	@PostMapping("/register")
	public ResponseEntity<Users> register(@Valid @RequestBody UserDto userDto) {
		Users user = userService.register(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody UserDto userDto) {
		String jwtToken = userService.verify(userDto);
		return new ResponseEntity<>(jwtToken, HttpStatus.OK);
	}
}
