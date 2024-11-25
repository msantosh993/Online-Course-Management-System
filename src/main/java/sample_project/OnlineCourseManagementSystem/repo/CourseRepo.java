package sample_project.OnlineCourseManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample_project.OnlineCourseManagementSystem.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

	Course findByCourseTitle(String courseTitle);
}
