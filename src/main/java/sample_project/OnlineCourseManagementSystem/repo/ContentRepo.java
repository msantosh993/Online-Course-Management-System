package sample_project.OnlineCourseManagementSystem.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample_project.OnlineCourseManagementSystem.model.Content;

@Repository
public interface ContentRepo extends JpaRepository<Content, Integer> {

	Optional<Content> findByContentTitle(String contentTitle);
    List<Content> findByCourseCourseId(Integer courseId);

}
