package sample_project.OnlineCourseManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.ReviewDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.CourseNotFound;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.ReviewNotFound;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.StudentNotFound;
import sample_project.OnlineCourseManagementSystem.model.Course;
import sample_project.OnlineCourseManagementSystem.model.Review;
import sample_project.OnlineCourseManagementSystem.model.Student;
import sample_project.OnlineCourseManagementSystem.repo.CourseRepo;
import sample_project.OnlineCourseManagementSystem.repo.ReviewRepo;
import sample_project.OnlineCourseManagementSystem.repo.StudentRepo;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepo reviewRepo;

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private CourseRepo courseRepo;

	public Review postReview(ReviewDto reviewDto) {
		Review review = new Review();
		review.setRatingStars(reviewDto.getRatingStars());
		review.setReviewedAt(reviewDto.getReviewedAt());
		Optional<Student> student = studentRepo.findById(reviewDto.getStudentId());
		if (student.isPresent()) {
			review.setStudent(student.get());
		} else {
			throw new StudentNotFound("Student Not Found With Id " + reviewDto.getStudentId());
		}
		Optional<Course> course = courseRepo.findById(reviewDto.getCourseId());
		if (course.isPresent()) {
			review.setCourse(course.get());
		} else {
			throw new CourseNotFound("Course not found with id " + reviewDto.getCourseId());
		}
		return reviewRepo.save(review);
	}

	public Review getReviewById(Integer reviewId) {
		Optional<Review> review = reviewRepo.findById(reviewId);
		if (review.isPresent()) {
			return review.get();
		} else {
			throw new ReviewNotFound("Review not found with id " + reviewId);
		}
	}

	public Review updateReview(Integer reviewId, ReviewDto reviewDto) {
		Optional<Review> existingReview = reviewRepo.findById(reviewId);
		if (existingReview.isPresent()) {
			Review review = existingReview.get();
			review.setRatingStars(reviewDto.getRatingStars());
			review.setReviewedAt(reviewDto.getReviewedAt());

			Optional<Student> student = studentRepo.findById(reviewDto.getStudentId());
			if (student.isPresent()) {
				review.setStudent(student.get());
			} else {
				throw new StudentNotFound("Student Not Found With Id " + reviewDto.getStudentId());
			}

			Optional<Course> course = courseRepo.findById(reviewDto.getCourseId());
			if (course.isPresent()) {
				review.setCourse(course.get());
			} else {
				throw new CourseNotFound("Course not found with id " + reviewDto.getCourseId());
			}
			return reviewRepo.save(review);
		} else {
			throw new ReviewNotFound("Review not found with id " + reviewId);
		}
	}

	public String deleteReview(Integer reviewId) {
		Optional<Review> review = reviewRepo.findById(reviewId);
		if (review.isPresent()) {
			reviewRepo.delete(review.get());
			return "Review deleted with id " + review.get().getReviewId();
		} else {
			throw new ReviewNotFound("Review not found with id " + reviewId);
		}
	}

	public List<Review> fetchAllReviews() {
		return reviewRepo.findAll();
	}
}
