package sample_project.OnlineCourseManagementSystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import sample_project.OnlineCourseManagementSystem.enums.Role;

@Entity
@Data
@Table(name = "tb_users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	private String username;
	private String password;
	private String emailId;
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String qualification;
	private String skills;
	private Integer experience;
	private String location;
	private String registeredAt;
	private String educationBackground;
	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Course> courses;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Enrollment> enrollments;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Review> reviews;
}
