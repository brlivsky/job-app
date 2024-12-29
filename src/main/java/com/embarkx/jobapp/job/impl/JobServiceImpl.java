package com.embarkx.jobapp.job.impl;

import com.embarkx.jobapp.job.Job;
import com.embarkx.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long jobIndex = 0L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(jobIndex++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job: jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean removeJob(Long id) {
        for (Job job: jobs) {
            if (job.getId().equals(id)) {
                return jobs.remove(job);
            }
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job newJob) {
        for (Job job: jobs) {
            if (job.getId().equals(id)) {

                job.setDescription(newJob.getDescription());
                job.setLocation(newJob.getLocation());
                job.setTitle(newJob.getTitle());
                job.setMaxSalary(newJob.getMaxSalary());
                job.setMinSalary(newJob.getMinSalary());

                return true;
            }
        }
        return false;
    }


}
