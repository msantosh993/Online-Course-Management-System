package sample_project.OnlineCourseManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample_project.OnlineCourseManagementSystem.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
	List<Review> findByCourseCourseId(Integer courseId);
}
