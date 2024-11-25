package sample_project.OnlineCourseManagementSystem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EnrollmentDto {
	@NotNull(message = "Date and time cannot be null")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$", message = "Date and time must be in the format yyyy-MM-dd'T'HH:mm:ss Example:2024-11-20T05:30:00")
	private String enrolledAt;
	@NotNull(message = "Student Id can't be null")
	private Integer studentId;
	@NotNull(message = "Course Id can't be null")
	private Integer courseId;
}
