package com.example.quartz_one.service;

import com.example.quartz_one.dao.JobAndTriggerDao;
import com.example.quartz_one.entity.JobAndTrigger;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IJobAndTriggerService {

    @Autowired
    private JobAndTriggerDao jobAndTriggerDao;


    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobAndTriggerDao.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
        return page;
    }
}
