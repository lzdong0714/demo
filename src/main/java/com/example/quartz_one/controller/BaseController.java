package com.example.quartz_one.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Liu Zhendong
 * @Description 固定的定时任务 用quzrtz是可以外部输入配置，获取定时字段
 * @createTime 2019年08月08日 10:37:00
 */
@Component
public class BaseController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = CrontabMap.crontab_1)
    public void scheduledTest() {
        logger.info("执行定时任务");
    }

    private String getCrontab() {
        return "0/5 * * * * ? ";
    }

    private enum CrontabList {
        second_5("0/5 * * * * ? "),
        second_10("0/5 * * * * ? "),
        second_15("0/5 * * * * ? ");

        private String contab;

        CrontabList(String contab){
            this.contab = contab;
        }

        public String getContab(){
            return this.contab;
        }

    }

}

class CrontabMap{
    public static final String crontab_1 = "0/5 * * * * ? ";
}
