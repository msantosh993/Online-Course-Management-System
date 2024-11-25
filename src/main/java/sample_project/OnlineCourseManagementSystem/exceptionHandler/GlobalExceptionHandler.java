package sample_project.OnlineCourseManagementSystem.exceptionHandler;

import java.time.LocalDateTime;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ApplicationErrors httpMessageNotReadableHandler(HttpMessageNotReadableException ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ApplicationErrors methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getFieldError().getDefaultMessage(),
				HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ApplicationErrors constraintViolationHandler(ConstraintViolationException ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = InstructorNotFound.class)
	public ApplicationErrors instructorNotFoundHandler(InstructorNotFound ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = StudentNotFound.class)
	public ApplicationErrors studentNotFoundHandler(StudentNotFound ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = CourseNotFound.class)
	public ApplicationErrors courseNotFoundHandler(CourseNotFound ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = ContentNotFound.class)
	public ApplicationErrors contentNotFoundHandler(ContentNotFound ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = EnrollmentNotFound.class)
	public ApplicationErrors enrollmentNotFoundHandler(EnrollmentNotFound ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = ContentAlreadyExist.class)
	public ApplicationErrors contentAlreadyExisHandler(ContentAlreadyExist ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.CONFLICT.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = CourseAlreadyExist.class)
	public ApplicationErrors courseAlreadyExisHandler(CourseAlreadyExist ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.CONFLICT.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = StudentAlreadyExist.class)
	public ApplicationErrors studentAlreadyExisHandler(StudentAlreadyExist ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.CONFLICT.getReasonPhrase());
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = UserAlreadyExist.class)
	public ApplicationErrors userAlreadyExisHandler(UserAlreadyExist ex) {
		return new ApplicationErrors(LocalDateTime.now(), ex.getMessage(), HttpStatus.CONFLICT.getReasonPhrase());
	}
}
