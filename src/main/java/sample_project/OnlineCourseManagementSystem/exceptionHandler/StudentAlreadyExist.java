package sample_project.OnlineCourseManagementSystem.exceptionHandler;

public class StudentAlreadyExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentAlreadyExist(String message) {
		super(message);
	}

}
