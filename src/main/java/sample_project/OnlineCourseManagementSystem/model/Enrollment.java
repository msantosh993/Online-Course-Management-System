package sample_project.OnlineCourseManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_enrollments")
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enrollmentId;
	private String enrolledAt;
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
}
