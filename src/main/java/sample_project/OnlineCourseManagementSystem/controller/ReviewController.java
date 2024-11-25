package sample_project.OnlineCourseManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import sample_project.OnlineCourseManagementSystem.dto.ReviewDto;
import sample_project.OnlineCourseManagementSystem.model.Review;
import sample_project.OnlineCourseManagementSystem.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Validated
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PostMapping("/postReview")
	public ResponseEntity<Review> postReview(@Valid @RequestBody ReviewDto reviewDto) {
		Review review = reviewService.postReview(reviewDto);
		return new ResponseEntity<>(review, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@GetMapping("/getReviewById/{reviewId}")
	public ResponseEntity<Review> getReviewById(@PathVariable Integer reviewId) {
		Review review = reviewService.getReviewById(reviewId);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@PutMapping("/updateReview/{reviewId}")
	public ResponseEntity<Review> updateReview(@PathVariable Integer reviewId,
			@Valid @RequestBody ReviewDto reviewDto) {
		Review review = reviewService.updateReview(reviewId, reviewDto);
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@DeleteMapping("/deleteReview/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Integer reviewId) {
		String response = reviewService.deleteReview(reviewId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@GetMapping("/getAllReviews")
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> reviews = reviewService.fetchAllReviews();
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}
}
