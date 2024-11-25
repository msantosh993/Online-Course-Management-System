package sample_project.OnlineCourseManagementSystem.exceptionHandler;

public class ContentAlreadyExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContentAlreadyExist(String message) {
		super(message);
	}

}
