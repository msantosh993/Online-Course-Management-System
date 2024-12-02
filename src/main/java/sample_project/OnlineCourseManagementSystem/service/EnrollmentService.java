package sample_project.OnlineCourseManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.EnrollmentDetails;
import sample_project.OnlineCourseManagementSystem.dto.EnrollmentDto;
import sample_project.OnlineCourseManagementSystem.dto.StudentDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.CourseNotFound;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.EnrollmentNotFound;
import sample_project.OnlineCourseManagementSystem.model.Course;
import sample_project.OnlineCourseManagementSystem.model.Enrollment;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.repo.CourseRepo;
import sample_project.OnlineCourseManagementSystem.repo.EnrollmentRepo;
import sample_project.OnlineCourseManagementSystem.repo.UsersRepo;

@Service
public class EnrollmentService {

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private UsersRepo studentRepo;

	@Autowired
	private EnrollmentRepo enrollmentRepo;

	@Autowired
	private EmailService emailService;

	public String enrollment(EnrollmentDto enrollmentDto) {
		Enrollment createdEnrollment = new Enrollment();
		Optional<Course> course = courseRepo.findById(enrollmentDto.getCourseId());
		if (course.isPresent()) {
			createdEnrollment.setCourse(course.get());
		} else {
			throw new CourseNotFound("Course not found with id " + enrollmentDto.getCourseId());
		}

		Optional<Users> student = studentRepo.findById(enrollmentDto.getStudentId());
		if (student.isPresent()) {
			createdEnrollment.setStudent(student.get());
		} else {
			throw new RuntimeException("Student not found with id " + enrollmentDto.getStudentId());
		}

		createdEnrollment.setEnrolledAt(enrollmentDto.getEnrolledAt());
		enrollmentRepo.save(createdEnrollment);
		String studentEmail = student.get().getEmailId();
		String courseName = course.get().getCourseTitle();
		String subject = "Welcome to " + courseName;
		String body = String.format(
				"Hello " + student.get().getUsername() + ", You have successfully enrolled in the course %s",
				courseName);
		emailService.sendEmail(studentEmail, subject, body);
		return "Your Successfully Enrolled with student id " + enrollmentDto.getStudentId() + " with course id "
				+ enrollmentDto.getCourseId() + " and your enrollment id is " + createdEnrollment.getEnrollmentId();
	}

	public EnrollmentDetails getEnrollment(Integer enrollmentId) {
		Optional<Enrollment> optionalEnrollment = enrollmentRepo.findById(enrollmentId);
		if (optionalEnrollment.isPresent()) {
			Enrollment enrollment = optionalEnrollment.get();
			EnrollmentDetails enrollmentDetails = new EnrollmentDetails();
			enrollmentDetails.setEnrolledAt(enrollment.getEnrolledAt());
			StudentDto student = new StudentDto();
			student.setUsername(enrollment.getStudent().getUsername());
			student.setPassword(enrollment.getStudent().getPassword());
			student.setEmailId(enrollment.getStudent().getEmailId());
			student.setPhoneNumber(enrollment.getStudent().getPhoneNumber());
			student.setRole(enrollment.getStudent().getRole().toString());
			student.setLocation(enrollment.getStudent().getLocation());
			student.setEducationBackground(enrollment.getStudent().getEducationBackground());
			student.setRegisteredAt(enrollment.getStudent().getRegisteredAt());
			enrollmentDetails.setStudent(student);
			enrollmentDetails.setCourse(enrollment.getCourse());
			return enrollmentDetails;

		} else {
			throw new EnrollmentNotFound("Enrollment Not Found with Id " + enrollmentId);
		}
	}

	public List<EnrollmentDetails> getAllEnrollments(Integer courseId) {
		List<Enrollment> enrollments = enrollmentRepo.findByCourseCourseId(courseId);
		List<EnrollmentDetails> listEnrollmentDetails = new ArrayList<>();
		for (Enrollment enrollment : enrollments) {
			EnrollmentDetails enrollmentDetails = new EnrollmentDetails();
			enrollmentDetails.setEnrolledAt(enrollment.getEnrolledAt());
			StudentDto student = new StudentDto();
			student.setUsername(enrollment.getStudent().getUsername());
			student.setPassword(enrollment.getStudent().getPassword());
			student.setEmailId(enrollment.getStudent().getEmailId());
			student.setPhoneNumber(enrollment.getStudent().getPhoneNumber());
			student.setRole(enrollment.getStudent().getRole().toString());
			student.setLocation(enrollment.getStudent().getLocation());
			student.setEducationBackground(enrollment.getStudent().getEducationBackground());
			student.setRegisteredAt(enrollment.getStudent().getRegisteredAt());
			enrollmentDetails.setStudent(student);
			enrollmentDetails.setCourse(enrollment.getCourse());
			listEnrollmentDetails.add(enrollmentDetails);
		}
		return listEnrollmentDetails;
	}
}
