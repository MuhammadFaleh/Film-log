package com.dev.filmlog.Controller;

import com.dev.filmlog.DTOIN.ReviewDTOIn;
import com.dev.filmlog.Model.Review;
import com.dev.filmlog.Model.User;
import com.dev.filmlog.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@AuthenticationPrincipal User user, @RequestBody ReviewDTOIn reviewDTOIn) {
        reviewService.addReview(user.getId(), reviewDTOIn);
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

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<?> updateReview(@AuthenticationPrincipal User user, @PathVariable Integer reviewId, @RequestBody ReviewDTOIn reviewDTOIn) {
        reviewService.updateReview(user.getId(), reviewId, reviewDTOIn);
        return ResponseEntity.status(200).body("Review updated successfully");
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@AuthenticationPrincipal User user, @PathVariable Integer reviewId) {
        reviewService.deleteReview(user.getId(), reviewId);
        return ResponseEntity.status(200).body("Review deleted successfully");
    }
}
