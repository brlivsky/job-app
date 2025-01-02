package com.embarkx.jobapp.job.impl;

import com.embarkx.jobapp.company.Company;
import com.embarkx.jobapp.company.CompanyRepository;
import com.embarkx.jobapp.job.Job;
import com.embarkx.jobapp.job.JobRepository;
import com.embarkx.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    final JobRepository jobRepository;
    final CompanyRepository companyRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public boolean createJob(Job job) {
        Company company = companyRepository.findById(job.getCompany().getId()).orElse(null);
        if (company != null) {
            job.setCompany(company);
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean removeJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job newJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setDescription(newJob.getDescription());
            job.setLocation(newJob.getLocation());
            job.setTitle(newJob.getTitle());
            job.setMaxSalary(newJob.getMaxSalary());
            job.setMinSalary(newJob.getMinSalary());

            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
