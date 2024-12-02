package sample_project.OnlineCourseManagementSystem.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	private String courseTitle;
	private String courseDescription;
	private String courseCreatedAt;
	@ManyToOne
	@JoinColumn(name = "instructor_id", nullable = false)
	@JsonBackReference
	private Users instructor;
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Content> contents = new ArrayList<>();
}
