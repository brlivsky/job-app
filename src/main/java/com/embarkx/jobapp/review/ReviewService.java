package com.embarkx.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Review getReview(Long companyId, Long reviewId);

    boolean addReview(Long companyId, Review review);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
