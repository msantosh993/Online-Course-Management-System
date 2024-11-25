package sample_project.OnlineCourseManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CourseDto {
	@NotBlank(message = "Course title can't be blank")
	@NotNull(message = "Course title can't be null")
	private String courseTitle;
	@NotBlank(message = "Course Description can't be blank")
	@NotNull(message = "Course Description can't be null")
	private String courseDescription;
	@NotNull(message = "Date and time cannot be null")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$", message = "Date and time must be in the format yyyy-MM-dd'T'HH:mm:ss Example:2024-11-20T05:30:00")
	private String courseCreatedAt;
	@NotNull(message = "Instructor Id can't be null")
	private Integer instructorId;
}
