package com.example.demo.service;

import com.example.demo.entity.ViewEntity;
import com.example.demo.mapper.ViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月09日 11:22:00
 */
@Service
public class ViewService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ViewMapper viewMapper;

    @Scheduled(cron = "0/10 * * * * *")
    public void showViewItems(){
        List<ViewEntity> viewItems = viewMapper.getViewItems();
        viewItems.forEach(item->{
            logger.info(item.toString());
        });
    }
}
