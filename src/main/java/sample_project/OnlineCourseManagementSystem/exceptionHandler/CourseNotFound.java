package sample_project.OnlineCourseManagementSystem.exceptionHandler;

public class CourseNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CourseNotFound(String message)
	{
		super(message);
	}

}
