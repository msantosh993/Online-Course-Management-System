package sample_project.OnlineCourseManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "tbl_contents")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contentId;
	private String contentTitle;
	private String contentCreatedAt;

	@JsonIgnore
	private byte[] contentFile;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	@JsonBackReference
	private Course course;
}
