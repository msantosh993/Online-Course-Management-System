package sample_project.OnlineCourseManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InstructorDto {
	@NotBlank(message = "Instructor Name can't be blank")
	@NotNull(message = "Instructor Name can't be null")
	private String instructorName;
	@NotNull(message = "Phone number cannot be null")
	@Pattern(regexp = "^\\+91[6-9]\\d{9}$", message = "Phone number must start with +91 and followed by 10 digits starting with 6, 7, 8, or 9")
	private String instructorPhoneNumber;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must end with @gmail.com")
	private String instructorEmailId;
	@NotBlank(message = "Instructor Qualification can't be blank")
	@NotNull(message = "Instructor Qualification can't be null")
	private String instructorQualification;
	@NotBlank(message = "Instructor Skills can't be blank")
	@NotNull(message = "Instructor Skills can't be null")
	private String instructorSkills;
	@NotNull(message = "Instructor Experience can't be null")
	private Integer instructorExperience;
}
