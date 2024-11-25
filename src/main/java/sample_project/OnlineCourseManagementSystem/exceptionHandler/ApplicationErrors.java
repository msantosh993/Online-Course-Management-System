package sample_project.OnlineCourseManagementSystem.exceptionHandler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ApplicationErrors {
	private LocalDateTime localDateTime;
	private String message;
	private String description;
}
