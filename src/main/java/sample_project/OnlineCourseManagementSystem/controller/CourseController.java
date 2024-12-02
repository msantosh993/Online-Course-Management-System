package sample_project.OnlineCourseManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import sample_project.OnlineCourseManagementSystem.dto.CourseDto;
import sample_project.OnlineCourseManagementSystem.model.Course;
import sample_project.OnlineCourseManagementSystem.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@Validated
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PostMapping("/createCourse")
	public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDto courseDto) {
		Course course = courseService.createCourse(courseDto);
		return new ResponseEntity<>(course, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_INSTRUCTOR','ROLE_STUDENT')")
	@GetMapping("/getCourseById/{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable Integer courseId) {
		Course course = courseService.findCourseById(courseId);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_INSTRUCTOR','ROLE_STUDENT')")
	@GetMapping("/getCourseByTitle/{courseTitle}")
	public ResponseEntity<Course> getCourseByTitle(@PathVariable String courseTitle) {
		Course course = courseService.findCourseByTitle(courseTitle);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PutMapping("/updateCourse/{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable Integer courseId,
			@Valid @RequestBody CourseDto courseDto) {
		Course updatedCourse = courseService.updateCourse(courseId, courseDto);
		return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@DeleteMapping("/deleteCourse/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable Integer courseId) {
		String response = courseService.deleteCourse(courseId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
