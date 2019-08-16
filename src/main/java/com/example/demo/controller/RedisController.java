package com.example.demo.controller;

import com.example.demo.entity.Friend;
import com.example.demo.util.RedisUtil;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author: Liu Zhendong
 * @Description windows cmd 执行：redis-cli.exe -h 47.92.160.232 -p 6379
 * 连接到云服务的redis
 * @createTime 2019年08月15日 15:38:00
 */

@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/set",method = RequestMethod.GET)
    public void setValue(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format_now = now.format(formatter);
        boolean b = redisUtil.set("test", format_now);
        logger.info("save success or not : " + b);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public void getValue(){
        Object test = redisUtil.get("test");
        logger.info(test.toString());
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void delValueByKey(){
        //TODO 删除测试暂时报错需要修改查原因
        Boolean b = redisTemplate.delete("test");
        logger.info("delete success or not : " + b);

    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.92.160.232", 6379);
        String test = jedis.get("test");
        jedis.connect();
        System.out.println(jedis.isConnected());
        System.out.println(jedis.ping());
    }
}
