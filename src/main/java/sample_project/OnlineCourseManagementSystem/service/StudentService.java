package sample_project.OnlineCourseManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.StudentDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.StudentAlreadyExist;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.StudentNotFound;
import sample_project.OnlineCourseManagementSystem.model.Student;
import sample_project.OnlineCourseManagementSystem.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;

	public Student createStudent(StudentDto studentDto) {
		Optional<Student> existedStudent = studentRepo.findByStudentEmailId(studentDto.getStudentEmailId());
		if (existedStudent.isPresent()) {
			throw new StudentAlreadyExist(
					"there is already a student existed with this email id please register with another email id");
		}
		Student student = new Student();
		student.setStudentName(studentDto.getStudentName());
		student.setStudentPhoneNumber(studentDto.getStudentPhoneNumber());
		student.setStudentEmailId(studentDto.getStudentEmailId());
		student.setStudentLocation(studentDto.getStudentLocation());
		student.setRegisteredAt(studentDto.getRegisteredAt());
		student.setStudentEducationBackGround(studentDto.getStudentEducationBackGround());
		return studentRepo.save(student);
	}

	public Student getStudentById(Integer id) {
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if (optionalStudent.isPresent()) {
			return optionalStudent.get();
		} else {
			throw new StudentNotFound("Student not found with ID: " + id);
		}
	}

	public Student getStudentByName(String studentName) {
		Student fetchedStudent = studentRepo.findByStudentName(studentName);
		if (fetchedStudent == null) {
			throw new StudentNotFound("Student not found with Name: " + studentName);
		}
		return fetchedStudent;
	}

	public Student updateStudent(Integer id, StudentDto studentDto) {
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			student.setStudentName(studentDto.getStudentName());
			student.setStudentPhoneNumber(studentDto.getStudentPhoneNumber());
			student.setStudentEmailId(studentDto.getStudentEmailId());
			student.setStudentLocation(studentDto.getStudentLocation());
			student.setRegisteredAt(studentDto.getRegisteredAt());
			student.setStudentEducationBackGround(studentDto.getStudentEducationBackGround());
			return studentRepo.save(student);
		} else {
			throw new StudentNotFound("Student not found with ID: " + id);
		}
	}

	public String deleteStudent(Integer id) {
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if (optionalStudent.isPresent()) {
			studentRepo.delete(optionalStudent.get());
			return "student deleted successfully with id " + optionalStudent.get().getStudentId();
		}
		throw new StudentNotFound("Student not found with ID: " + id);
	}

	public List<Student> findAllStudents() {
		return studentRepo.findAll();
	}
}
