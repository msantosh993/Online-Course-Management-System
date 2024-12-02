package sample_project.OnlineCourseManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sample_project.OnlineCourseManagementSystem.enums.Role;
import sample_project.OnlineCourseManagementSystem.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
	Users findByusername(String username);
	Users findByRole(Role role);

}
