package com.embarkx.jobapp.company.impl;

import com.embarkx.jobapp.company.Company;
import com.embarkx.jobapp.company.CompanyRepository;
import com.embarkx.jobapp.company.CompanyService;
import com.embarkx.jobapp.job.Job;
import com.embarkx.jobapp.job.JobRepository;
import com.embarkx.jobapp.review.Review;
import com.embarkx.jobapp.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    final CompanyRepository companyRepository;
    final JobRepository jobRepository;
    final ReviewRepository reviewRepository;

    @Autowired
    CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository, ReviewRepository reviewRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean updateCompany(Long id, Company newCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setDescription(newCompany.getDescription());
            company.setName(newCompany.getName());
            company.setJobs(newCompany.getJobs());

            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public Boolean createCompany(Company company) {
        try {
            companyRepository.save(company);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeCompany(Long id) {
        try {
            Optional<Company> optionalCompany = companyRepository.findById(id);
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();

                for (Job job : company.getJobs()) {
                    jobRepository.deleteById(job.getId());
                }

                for (Review review : company.getReview()) {
                    reviewRepository.deleteById(review.getId());
                }

                companyRepository.deleteById(id);
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }


}
