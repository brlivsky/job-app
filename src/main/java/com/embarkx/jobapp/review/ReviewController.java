package com.embarkx.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (reviews != null) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    ResponseEntity<Review> getReviewReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReview(companyId, reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        if (reviewService.addReview(companyId, review)) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{reviewId}")
    ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        if (reviewService.updateReview(companyId, reviewId, review)) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company/review not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        if (reviewService.deleteReview(companyId, reviewId)) {
            return new ResponseEntity<>("Deletion successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company/Review not found", HttpStatus.NOT_FOUND);
    }

}
