package sample_project.OnlineCourseManagementSystem.exceptionHandler;

public class UserNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFound(String message) {
		super(message);
	}

}
