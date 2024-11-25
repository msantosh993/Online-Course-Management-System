package sample_project.OnlineCourseManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_instructors")
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer instructorId;
	private String instructorName;
	private String instructorPhoneNumber;
	private String instructorEmailId;
	private String instructorQualification;
	private String instructorSkills;
	private Integer instructorExperience;

	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Course> courses = new ArrayList<>();
}
