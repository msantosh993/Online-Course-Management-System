package sample_project.OnlineCourseManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import sample_project.OnlineCourseManagementSystem.enums.Role;

@Entity
@Data
@Table(name = "tbl_users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String emailId;
	@Enumerated(EnumType.STRING)
	private Role role;
}
