package sample_project.OnlineCourseManagementSystem.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample_project.OnlineCourseManagementSystem.model.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Integer> {
	List<Enrollment> findByCourseCourseId(Integer courseId);
}
