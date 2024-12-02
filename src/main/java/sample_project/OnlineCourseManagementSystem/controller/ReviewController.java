package sample_project.OnlineCourseManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import sample_project.OnlineCourseManagementSystem.dto.ReviewDetails;
import sample_project.OnlineCourseManagementSystem.dto.ReviewDto;
import sample_project.OnlineCourseManagementSystem.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@Validated
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PostMapping("/postReview")
	public ResponseEntity<String> postReview(@Valid @RequestBody ReviewDto reviewDto) {
		String review = reviewService.postReview(reviewDto);
		return new ResponseEntity<>(review, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@GetMapping("/getReviewById/{reviewId}")
	public ResponseEntity<ReviewDetails> getReviewById(@PathVariable Integer reviewId) {
		ReviewDetails review = reviewService.getReviewById(reviewId);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PutMapping("/updateReview/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Integer reviewId,
			@Valid @RequestBody ReviewDto reviewDto) {
		String review = reviewService.updateReview(reviewId, reviewDto);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@DeleteMapping("/deleteReview/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Integer reviewId) {
		String response = reviewService.deleteReview(reviewId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@GetMapping("/getAllReviews/{courseId}")
	public ResponseEntity<List<ReviewDetails>> getAllReviews(@PathVariable Integer courseId) {
		List<ReviewDetails> reviews = reviewService.fetchAllReviews(courseId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}
}
