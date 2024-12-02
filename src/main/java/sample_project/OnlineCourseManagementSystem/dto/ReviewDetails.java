package sample_project.OnlineCourseManagementSystem.dto;

import lombok.Data;
import sample_project.OnlineCourseManagementSystem.model.Course;

@Data
public class ReviewDetails {
	private Integer ratingStars;
	private String reviewedAt;
	private StudentDto student;
	private Course course;
}
