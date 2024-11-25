package sample_project.OnlineCourseManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
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
	@NotBlank(message = "User Role can't be blank")
	@NotNull(message = "User Role can't be null")
	@Pattern(regexp = "(?i)^(Role_Instructor|Role_Student)$", message = "Role must be either 'Role_Instructor' or 'Role_Student' (case insensitive)")
	private String role;
}
