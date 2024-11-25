package sample_project.OnlineCourseManagementSystem.exceptionHandler;

public class InstructorNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InstructorNotFound(String message)
	{
		super(message);
	}
	
}
