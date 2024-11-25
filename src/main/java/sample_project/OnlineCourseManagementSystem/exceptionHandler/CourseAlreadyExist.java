package sample_project.OnlineCourseManagementSystem.exceptionHandler;

public class CourseAlreadyExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseAlreadyExist(String message) {
		super(message);
	}

}
