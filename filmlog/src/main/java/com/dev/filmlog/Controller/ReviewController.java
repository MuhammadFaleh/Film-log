package com.dev.filmlog.Controller;

import com.dev.filmlog.DTOIN.ReviewDTOIn;
import com.dev.filmlog.Model.Review;
import com.dev.filmlog.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addReview(@PathVariable Integer userId, @RequestBody ReviewDTOIn reviewDTOIn) {
        reviewService.addReview(userId, reviewDTOIn);
        return ResponseEntity.status(200).body("Review added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.status(200).body(reviewService.getAllReviews());
    }

    @GetMapping("/get/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Integer reviewId) {
        return ResponseEntity.status(200).body(reviewService.getReviewById(reviewId));
    }

    @PutMapping("/update/{userId}/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Integer userId, @PathVariable Integer reviewId, @RequestBody ReviewDTOIn reviewDTOIn) {
        reviewService.updateReview(userId, reviewId, reviewDTOIn);
        return ResponseEntity.status(200).body("Review updated successfully");
    }

    @DeleteMapping("/delete/{userId}/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer userId, @PathVariable Integer reviewId) {
        reviewService.deleteReview(userId, reviewId);
        return ResponseEntity.status(200).body("Review deleted successfully");
    }
}
