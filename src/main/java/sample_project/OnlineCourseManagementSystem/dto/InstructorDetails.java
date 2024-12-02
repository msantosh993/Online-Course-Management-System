package sample_project.OnlineCourseManagementSystem.dto;

import java.util.List;

import lombok.Data;
import sample_project.OnlineCourseManagementSystem.model.Course;

@Data
public class InstructorDetails {
	private String instructorName;
	private String password;
	private String emailId;
	private String phoneNumber;
	private String role;
	private String qualification;
	private String skills;
	private Integer experience;
	private List<Course> courses;
}
