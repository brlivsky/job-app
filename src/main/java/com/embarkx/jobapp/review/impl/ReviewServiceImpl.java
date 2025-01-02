package com.embarkx.jobapp.review.impl;

import com.embarkx.jobapp.company.Company;
import com.embarkx.jobapp.company.CompanyRepository;
import com.embarkx.jobapp.review.Review;
import com.embarkx.jobapp.review.ReviewRepository;
import com.embarkx.jobapp.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    final ReviewRepository reviewRepository;
    final CompanyRepository companyRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        if (companyRepository.existsById(companyId)) { // todo: optimise
            return reviewRepository.findByCompanyId(companyId);
        }
        return null;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {
            return reviewRepository.findById(reviewId).orElse(null);
        }
        return null;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {

            Review review = reviewRepository.findById(reviewId).orElse(null);
            if (review != null) {

                if (companyId.equals(review.getCompany().getId())) {
                    reviewRepository.deleteById(reviewId);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review newReview) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {

            Review review = reviewRepository.findById(reviewId).orElse(null);
            if (review != null) {

                if (companyId.equals(review.getCompany().getId())) {

                    review.setTitle(newReview.getTitle());
                    review.setDescription(newReview.getDescription());
                    review.setRating(newReview.getRating());
                    reviewRepository.save(review);

                    return true;
                }
            }
        }
        return false;
    }
}
