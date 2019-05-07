package com.example.quartz_one.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job {
    @Override
    default void execute(JobExecutionContext context) throws JobExecutionException {}
}
