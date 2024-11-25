package sample_project.OnlineCourseManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentDto {
	@NotBlank(message = "Student Name can't be blank")
	@NotNull(message = "Student Name can't be null")
	private String studentName;
	@NotNull(message = "Phone number cannot be null")
	@Pattern(regexp = "^\\+91[6-9]\\d{9}$", message = "Phone number must start with +91 and followed by 10 digits starting with 6, 7, 8, or 9")
	private String studentPhoneNumber;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must end with @gmail.com")
	private String studentEmailId;
	@NotBlank(message = "Student Location can't be blank")
	@NotNull(message = "Student Location can't be null")
	private String studentLocation;
	@NotNull(message = "Date and time cannot be null")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$", message = "Date and time must be in the format yyyy-MM-dd'T'HH:mm:ss Example:2024-11-20T05:30:00")
	private String registeredAt;
	@NotBlank(message = "Student Education Background can't be blank")
	@NotNull(message = "Student Education Background can't be null")
	private String studentEducationBackGround;
}
