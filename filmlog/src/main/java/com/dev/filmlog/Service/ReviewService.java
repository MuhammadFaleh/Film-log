package com.dev.filmlog.Service;

import com.dev.filmlog.Api.ApiException;
import com.dev.filmlog.DTO.In.ReviewDTOIn;
import com.dev.filmlog.Model.Media;
import com.dev.filmlog.Model.Profile;
import com.dev.filmlog.Model.Review;
import com.dev.filmlog.Repository.MediaRepository;
import com.dev.filmlog.Repository.ProfileRepository;
import com.dev.filmlog.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProfileRepository profileRepository;
    private final MediaRepository mediaRepository;

    public void addReview(Integer userId, ReviewDTOIn reviewDTOIn){
        Profile profile=profileRepository.findProfileById(userId);
        if (profile==null){
            throw new ApiException("Profile not found");
        }
        Media media=mediaRepository.findMediaById(reviewDTOIn.getMediaId());
        if (media==null){
            throw new ApiException("Media not found");
        }
        Review review=new Review(null,reviewDTOIn.getTitle(), reviewDTOIn.getDescription(), reviewDTOIn.getRating(), LocalDateTime.now(),media,profile);
        reviewRepository.save(review);
    }
    @Cacheable("reviews")
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review getReviewById(Integer reviewId){
        return reviewRepository.findReviewsById(reviewId);
    }

    public void updateReview(Integer userId, Integer reviewId, ReviewDTOIn reviewDTOIn){
        Profile profile=profileRepository.findProfileById(userId);
        if (profile==null){
            throw new ApiException("Profile not found");
        }
        Review oldReview =reviewRepository.findReviewsById(reviewId);
        if (oldReview ==null){
            throw new ApiException("Review not found");
        }
        if (!oldReview.getProfile().equals(profile)) {
            throw new ApiException("You don't own this review");
        }

        oldReview.setTitle(reviewDTOIn.getTitle());
        oldReview.setDescription(reviewDTOIn.getDescription());
        oldReview.setRating(reviewDTOIn.getRating());
        reviewRepository.save(oldReview);
    }

    public void deleteReview(Integer userId, Integer reviewId){
        Profile profile=profileRepository.findProfileById(userId);
        if (profile==null){
            throw new ApiException("Profile not found");
        }
        Review review =reviewRepository.findReviewsById(reviewId);
        if (review ==null){
            throw new ApiException("Review not found");
        }
        if (!review.getProfile().equals(profile)) {
            throw new ApiException("You don't own this review");
        }

        reviewRepository.delete(review);
    }
}
