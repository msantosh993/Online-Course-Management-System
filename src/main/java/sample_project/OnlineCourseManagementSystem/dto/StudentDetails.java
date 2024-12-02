package sample_project.OnlineCourseManagementSystem.dto;

import lombok.Data;

@Data
public class StudentDetails {
	private String studentName;
	private String password;
	private String emailId;
	private String phoneNumber;
	private String role;
	private String location;
	private String educationBackground;
	private String registeredAt;
}
