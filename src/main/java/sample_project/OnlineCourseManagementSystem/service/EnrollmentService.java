package sample_project.OnlineCourseManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.EnrollmentDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.CourseNotFound;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.EnrollmentNotFound;
import sample_project.OnlineCourseManagementSystem.model.Course;
import sample_project.OnlineCourseManagementSystem.model.Enrollment;
import sample_project.OnlineCourseManagementSystem.model.Student;
import sample_project.OnlineCourseManagementSystem.repo.CourseRepo;
import sample_project.OnlineCourseManagementSystem.repo.EnrollmentRepo;
import sample_project.OnlineCourseManagementSystem.repo.StudentRepo;

@Service
public class EnrollmentService {

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private EnrollmentRepo enrollmentRepo;

	@Autowired
	private EmailService emailService;

	public Enrollment enrollment(EnrollmentDto enrollmentDto) {
		Enrollment createdEnrollment = new Enrollment();
		Optional<Course> course = courseRepo.findById(enrollmentDto.getCourseId());
		if (course.isPresent()) {
			createdEnrollment.setCourse(course.get());
		} else {
			throw new CourseNotFound("Course not found with id " + enrollmentDto.getCourseId());
		}

		Optional<Student> student = studentRepo.findById(enrollmentDto.getStudentId());
		if (student.isPresent()) {
			createdEnrollment.setStudent(student.get());
		} else {
			throw new RuntimeException("Student not found with id " + enrollmentDto.getStudentId());
		}

		createdEnrollment.setEnrolledAt(enrollmentDto.getEnrolledAt());
		Enrollment savedEnrollment = enrollmentRepo.save(createdEnrollment);
		String studentEmail = student.get().getStudentEmailId();
		String courseName = course.get().getCourseTitle();
		String subject = "Welcome to " + courseName;
		String body = String.format(
				"Hello " + student.get().getStudentName() + ", You have successfully enrolled in the course %s",
				courseName);
		emailService.sendEmail(studentEmail, subject, body);
		return savedEnrollment;
	}

	public Enrollment getEnrollment(Integer enrollmentId) {
		Optional<Enrollment> optionalEnrollment = enrollmentRepo.findById(enrollmentId);
		if (optionalEnrollment.isPresent()) {
			return optionalEnrollment.get();
		} else {
			throw new EnrollmentNotFound("Enrollment Not Found with Id " + enrollmentId);
		}
	}

	public List<Enrollment> getAllEnrollments() {
		return enrollmentRepo.findAll();
	}
}
