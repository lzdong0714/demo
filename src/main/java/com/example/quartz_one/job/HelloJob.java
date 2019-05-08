package com.example.quartz_one.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

import java.util.Date;

@Slf4j
public class HelloJob implements BaseJob{
    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        log.error("Hello Job 执行时间 : "+new Date());
    }
}
