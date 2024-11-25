package sample_project.OnlineCourseManagementSystem.exceptionHandler;

public class EnrollmentNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnrollmentNotFound(String message) {
		super(message);
	}

}
