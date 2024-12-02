package sample_project.OnlineCourseManagementSystem.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample_project.OnlineCourseManagementSystem.dto.CourseDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.CourseAlreadyExist;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.CourseNotFound;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.UserNotFound;
import sample_project.OnlineCourseManagementSystem.model.Course;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.repo.CourseRepo;
import sample_project.OnlineCourseManagementSystem.repo.UsersRepo;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private UsersRepo userRepo;

	public Course createCourse(CourseDto courseDto) {
		Course existedCourse = courseRepo.findByCourseTitle(courseDto.getCourseTitle());
		if (existedCourse != null) {
			throw new CourseAlreadyExist(
					"there is already a course existed with that name you have to create a course with another name");
		}
		Course course = new Course();
		Optional<Users> instructor = userRepo.findById(courseDto.getInstructorId());
		if (instructor.isPresent()) {
			course.setInstructor(instructor.get());
			course.setCourseTitle(courseDto.getCourseTitle());
			course.setCourseDescription(courseDto.getCourseDescription());
			course.setCourseCreatedAt(courseDto.getCourseCreatedAt());
			return courseRepo.save(course);
		}
		throw new UserNotFound("Instructor not found with ID: " + courseDto.getInstructorId());

	}

	public Course findCourseById(Integer courseId) {
		Optional<Course> course = courseRepo.findById(courseId);
		if (course.isPresent()) {
			return course.get();
		}
		throw new CourseNotFound("Course not found with ID: " + courseId);
	}

	public Course findCourseByTitle(String courseTitle) {
		Course course = courseRepo.findByCourseTitle(courseTitle);
		if (course != null) {
			return course;
		}
		throw new CourseNotFound("Course not found with title: " + courseTitle);
	}

	public Course updateCourse(Integer courseId, CourseDto courseDto) {
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		if (optionalCourse.isPresent()) {
			Course existingCourse = optionalCourse.get();
			existingCourse.setCourseTitle(courseDto.getCourseTitle());
			existingCourse.setCourseDescription(courseDto.getCourseDescription());
			existingCourse.setCourseCreatedAt(courseDto.getCourseCreatedAt());
			Optional<Users> instructor = userRepo.findById(courseDto.getInstructorId());
			if (instructor.isPresent()) {
				existingCourse.setInstructor(instructor.get());
			} else {
				throw new UserNotFound("Instructor not found with ID: " + courseDto.getInstructorId());
			}
			return courseRepo.save(existingCourse);
		}
		throw new CourseNotFound("Course not found with ID: " + courseId);
	}

	public String deleteCourse(Integer courseId) {
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		if (optionalCourse.isPresent()) {
			courseRepo.delete(optionalCourse.get());
			return "Course deleted successfull with id " + optionalCourse.get().getCourseId();
		} else {
			throw new CourseNotFound("Course not found with ID: " + courseId);
		}
	}

}
