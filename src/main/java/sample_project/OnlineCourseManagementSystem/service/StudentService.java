package sample_project.OnlineCourseManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.StudentDetails;
import sample_project.OnlineCourseManagementSystem.dto.StudentDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.UserNotFound;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.repo.UsersRepo;

@Service
public class StudentService {

	@Autowired
	private UsersRepo userRepo;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public StudentDetails findStudentById(Integer id) {
		Optional<Users> student = userRepo.findById(id);
		if (student.isPresent() && (student.get().getRole().toString()).equals("ROLE_STUDENT")) {
			Users fetchedStudent = student.get();
			StudentDetails resultedStudent = new StudentDetails();
			resultedStudent.setStudentName(fetchedStudent.getUsername());
			resultedStudent.setPassword(fetchedStudent.getPassword());
			resultedStudent.setEmailId(fetchedStudent.getEmailId());
			resultedStudent.setPhoneNumber(fetchedStudent.getPhoneNumber());
			resultedStudent.setRole(fetchedStudent.getRole().toString());
			resultedStudent.setLocation(fetchedStudent.getLocation());
			resultedStudent.setEducationBackground(fetchedStudent.getEducationBackground());
			resultedStudent.setRegisteredAt(fetchedStudent.getRegisteredAt());
			return resultedStudent;
		}
		throw new UserNotFound("Student not found with id: " + id);
	}

	public StudentDetails findStudentByUserName(String username) {
		Users student = userRepo.findByusername(username);
		if (student != null && (student.getRole().toString()).equals("ROLE_STUDENT")) {
			StudentDetails resultedStudent = new StudentDetails();
			resultedStudent.setStudentName(student.getUsername());
			resultedStudent.setPassword(student.getPassword());
			resultedStudent.setEmailId(student.getEmailId());
			resultedStudent.setPhoneNumber(student.getPhoneNumber());
			resultedStudent.setRole(student.getRole().toString());
			resultedStudent.setLocation(student.getLocation());
			resultedStudent.setEducationBackground(student.getEducationBackground());
			resultedStudent.setRegisteredAt(student.getRegisteredAt());
			return resultedStudent;
		}
		throw new UserNotFound("Student not found with username: " + username);
	}

	public String updateStudentById(Integer id, StudentDto studentDto) {
		Optional<Users> student = userRepo.findById(id);
		if (student.isPresent() && (student.get().getRole().toString()).equals("ROLE_STUDENT")) {
			Users existingStudent = student.get();
			if (studentDto.getRole() == null || !studentDto.getRole().equalsIgnoreCase("ROLE_STUDENT")) {
				throw new IllegalArgumentException("Invalid role specified. Allowed role: ROLE_STUDENT.");
			}
			sample_project.OnlineCourseManagementSystem.enums.Role userRole;
			try {
				userRole = sample_project.OnlineCourseManagementSystem.enums.Role
						.valueOf(studentDto.getRole().toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Invalid role: " + studentDto.getRole());
			}
			existingStudent.setUsername(studentDto.getUsername());
			existingStudent.setPassword(encoder.encode(studentDto.getPassword()));
			existingStudent.setEmailId(studentDto.getEmailId());
			existingStudent.setPhoneNumber(studentDto.getPhoneNumber());
			existingStudent.setRole(userRole);
			existingStudent.setLocation(studentDto.getLocation());
			existingStudent.setRegisteredAt(studentDto.getRegisteredAt());
			existingStudent.setEducationBackground(studentDto.getEducationBackground());
			userRepo.save(existingStudent);
			return "Student with id " + id + " details updated successfully";
		}
		throw new UserNotFound("Student not found with Id: " + id);
	}

	public String deleteStudent(Integer id) {
		Optional<Users> student = userRepo.findById(id);
		if (student.isPresent() && (student.get().getRole().toString()).equals("ROLE_STUDENT")) {
			userRepo.delete(student.get());
			return "Student deleted successfully with id: " + id;
		}
		throw new UserNotFound("Student not found with id: " + id);
	}
}
