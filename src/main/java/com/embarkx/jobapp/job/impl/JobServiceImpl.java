package com.embarkx.jobapp.job.impl;

import com.embarkx.jobapp.job.Job;
import com.embarkx.jobapp.job.JobRepository;
import com.embarkx.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
//    private Long jobIndex = 0L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
//        job.setId(jobIndex++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean removeJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception IllegalArgumentException) { // updtated Exception e
            return false;
        }
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
