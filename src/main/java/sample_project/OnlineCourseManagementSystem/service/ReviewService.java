package sample_project.OnlineCourseManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.ReviewDetails;
import sample_project.OnlineCourseManagementSystem.dto.ReviewDto;
import sample_project.OnlineCourseManagementSystem.dto.StudentDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.CourseNotFound;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.ReviewNotFound;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.UserNotFound;
import sample_project.OnlineCourseManagementSystem.model.Course;
import sample_project.OnlineCourseManagementSystem.model.Review;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.repo.CourseRepo;
import sample_project.OnlineCourseManagementSystem.repo.ReviewRepo;
import sample_project.OnlineCourseManagementSystem.repo.UsersRepo;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepo reviewRepo;

	@Autowired
	private UsersRepo studentRepo;

	@Autowired
	private CourseRepo courseRepo;

	public String postReview(ReviewDto reviewDto) {
		Review review = new Review();
		review.setRatingStars(reviewDto.getRatingStars());
		review.setReviewedAt(reviewDto.getReviewedAt());
		Optional<Users> student = studentRepo.findById(reviewDto.getStudentId());
		if (student.isPresent()) {
			review.setStudent(student.get());
		} else {
			throw new UserNotFound("Student Not Found With Id " + reviewDto.getStudentId());
		}
		Optional<Course> course = courseRepo.findById(reviewDto.getCourseId());
		if (course.isPresent()) {
			review.setCourse(course.get());
		} else {
			throw new CourseNotFound("Course not found with id " + reviewDto.getCourseId());
		}
		reviewRepo.save(review);
		return "Your Review successfully submitted for course " + review.getCourse().getCourseTitle()
				+ " your review id is " + review.getReviewId();
	}

	public ReviewDetails getReviewById(Integer reviewId) {
		Optional<Review> review = reviewRepo.findById(reviewId);
		if (review.isPresent()) {
			Review fetchedReview = review.get();
			ReviewDetails reviewDetails = new ReviewDetails();
			reviewDetails.setReviewedAt(fetchedReview.getReviewedAt());
			reviewDetails.setRatingStars(fetchedReview.getRatingStars());
			reviewDetails.setCourse(fetchedReview.getCourse());
			StudentDto student = new StudentDto();
			student.setUsername(fetchedReview.getStudent().getUsername());
			student.setPassword(fetchedReview.getStudent().getPassword());
			student.setEmailId(fetchedReview.getStudent().getEmailId());
			student.setPhoneNumber(fetchedReview.getStudent().getPhoneNumber());
			student.setRole(fetchedReview.getStudent().getRole().toString());
			student.setLocation(fetchedReview.getStudent().getLocation());
			student.setEducationBackground(fetchedReview.getStudent().getEducationBackground());
			student.setRegisteredAt(fetchedReview.getStudent().getRegisteredAt());
			reviewDetails.setStudent(student);
			return reviewDetails;
		} else {
			throw new ReviewNotFound("Review not found with id " + reviewId);
		}
	}

	public String updateReview(Integer reviewId, ReviewDto reviewDto) {
		Optional<Review> existingReview = reviewRepo.findById(reviewId);
		if (existingReview.isPresent()) {
			Review review = existingReview.get();
			review.setRatingStars(reviewDto.getRatingStars());
			review.setReviewedAt(reviewDto.getReviewedAt());

			Optional<Users> student = studentRepo.findById(reviewDto.getStudentId());
			if (student.isPresent()) {
				review.setStudent(student.get());
			} else {
				throw new UserNotFound("Student Not Found With Id " + reviewDto.getStudentId());
			}

			Optional<Course> course = courseRepo.findById(reviewDto.getCourseId());
			if (course.isPresent()) {
				review.setCourse(course.get());
			} else {
				throw new CourseNotFound("Course not found with id " + reviewDto.getCourseId());
			}
			reviewRepo.save(review);
			return "Your review for course " + review.getCourse().getCourseTitle() + " is updated successfully";
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

	public List<ReviewDetails> fetchAllReviews(Integer courseId) {
		List<Review> reviewList = reviewRepo.findByCourseCourseId(courseId);
		List<ReviewDetails> listOfReviewDetails = new ArrayList<>();
		for (Review review : reviewList) {
			ReviewDetails reviewDetails = new ReviewDetails();
			reviewDetails.setReviewedAt(review.getReviewedAt());
			reviewDetails.setRatingStars(review.getRatingStars());
			reviewDetails.setCourse(review.getCourse());
			StudentDto student = new StudentDto();
			student.setUsername(review.getStudent().getUsername());
			student.setPassword(review.getStudent().getPassword());
			student.setEmailId(review.getStudent().getEmailId());
			student.setPhoneNumber(review.getStudent().getPhoneNumber());
			student.setRole(review.getStudent().getRole().toString());
			student.setLocation(review.getStudent().getLocation());
			student.setEducationBackground(review.getStudent().getEducationBackground());
			student.setRegisteredAt(review.getStudent().getRegisteredAt());
			reviewDetails.setStudent(student);
			reviewDetails.setCourse(review.getCourse());
			listOfReviewDetails.add(reviewDetails);
		}
		return listOfReviewDetails;
	}
}
