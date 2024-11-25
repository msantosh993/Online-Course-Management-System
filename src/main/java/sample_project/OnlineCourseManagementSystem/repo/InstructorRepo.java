package sample_project.OnlineCourseManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample_project.OnlineCourseManagementSystem.model.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Integer> {
}
