package sample_project.OnlineCourseManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InstructorDto {
	@NotBlank(message = "User Name can't be blank")
	@NotNull(message = "User Name can't be null")
	private String username;

	@NotBlank(message = "User Password can't be blank")
	@NotNull(message = "User Password can't be null")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^[A-Z](?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$", message = "Password must start with a capital letter, contain at least one digit, one special character, and include both letters and digits")
	private String password;

	@NotNull(message = "Email cannot be null")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must end with @gmail.com")
	private String emailId;

	@NotNull(message = "Phone number cannot be null")
	@Pattern(regexp = "^\\+91[6-9]\\d{9}$", message = "Phone number must start with +91 and followed by 10 digits starting with 6, 7, 8, or 9")
	private String phoneNumber;

	@NotBlank(message = "User Role can't be blank")
	@NotNull(message = "User Role can't be null")
	@Pattern(regexp = "(?i)^(Role_Instructor|Role_Student)$", message = "Role must be either 'Role_Instructor' or 'Role_Student' (case insensitive)")
	private String role;
	@NotBlank(message = "Instructor qualification can't be blank")
	@NotNull(message = "Instructor qualification can't be null")
	private String qualification;

	@NotBlank(message = "Instructor skills can't be blank")
	@NotNull(message = "Instructor skills can't be null")
	private String skills;

	@NotNull(message = "Instructor experience can't be null")
	private Integer experience;
}
