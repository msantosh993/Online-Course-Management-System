package sample_project.OnlineCourseManagementSystem.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample_project.OnlineCourseManagementSystem.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	Student findByStudentName(String studentName);

	Optional<Student> findByStudentEmailId(String studentEmailId);
}
