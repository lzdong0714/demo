package com.example.quartz_one.dao;

import com.example.quartz_one.entity.JobAndTrigger;

import java.util.List;

public interface JobAndTriggerDao {
    public List<JobAndTrigger> getJobAndTriggerDetails();
}
