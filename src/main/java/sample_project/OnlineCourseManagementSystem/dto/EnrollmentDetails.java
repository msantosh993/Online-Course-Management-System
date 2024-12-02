package sample_project.OnlineCourseManagementSystem.dto;

import lombok.Data;
import sample_project.OnlineCourseManagementSystem.model.Course;

@Data
public class EnrollmentDetails {
	private String enrolledAt;
	private StudentDto student;
	private Course course;
}
