package com.example.stomp.action;

import com.example.stomp.entity.ServerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月01日 11:37:00
 */

@RestController
@RequestMapping(value = "/send")
public class ServerSendMsg {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @SubscribeMapping("/topic/subscribeTest")

    @RequestMapping(value = "/send_topic",method = RequestMethod.GET)
    public void templateTest(){
        messagingTemplate.convertAndSend("/topic/subscribeTest",
                new ServerMessage("服务器主动推的数据"));
    }
}
