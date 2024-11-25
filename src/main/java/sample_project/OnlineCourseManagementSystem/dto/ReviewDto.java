package sample_project.OnlineCourseManagementSystem.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReviewDto {
	@NotNull(message = "Rating Stars can't be null")
	@Min(value = 1, message = "minimum rating is 1")
	@Max(value = 5, message = "maximum rating is 5")
	private Integer ratingStars;
	@NotNull(message = "Date and time cannot be null")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$", message = "Date and time must be in the format yyyy-MM-dd'T'HH:mm:ss Example:2024-11-20T05:30:00")
	private String reviewedAt;
	@NotNull(message = "Student Id can't be null")
	private Integer studentId;
	@NotNull(message = "Course Id can't be null")
	private Integer courseId;
}
