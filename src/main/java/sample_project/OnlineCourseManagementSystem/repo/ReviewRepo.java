package sample_project.OnlineCourseManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample_project.OnlineCourseManagementSystem.model.Course;
import sample_project.OnlineCourseManagementSystem.model.Review;
import sample_project.OnlineCourseManagementSystem.model.Student;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
	boolean existsByStudentAndCourse(Student student, Course course);
}
