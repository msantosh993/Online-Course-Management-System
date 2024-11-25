package sample_project.OnlineCourseManagementSystem.exceptionHandler;

public class ContentNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ContentNotFound(String message)
	{
		super(message);
	}
	
}
