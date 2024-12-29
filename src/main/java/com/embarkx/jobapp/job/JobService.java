package com.embarkx.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
    boolean removeJob(Long id);
    boolean updateJob(Long id, Job job);
}
