package com.example.stomp.action;

import com.example.stomp.entity.ClientMessage;
import com.example.stomp.entity.ServerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年07月31日 17:21:00
 */
@Controller
public class WebSocketAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @MessageMapping("/sendTest")
    @SendTo("/topic/subscribeTest")//发送给前端消息，映射路径（topic是配置设定好的路径，subscribeTest是子主题，道理同restful）
    public ServerMessage sendDemo(ClientMessage message) {
        logger.info("接收到了信息" + message.getName());
        return new ServerMessage("你发送的消息为:" + message.getName());
    }

    //接收前端的订阅
    @SubscribeMapping("/subscribeTest")//接收前端订阅主题，映射路径(根据config类的配置，
    // 加载了前缀 /demo 所以前端的映射路径是 /demo/subscribeTest
    //在stomp.html 中是连接时启动订阅
    public ServerMessage sub() {
        logger.info("XXX用户订阅了我。。。");
        return new ServerMessage("感谢你订阅了我。。。");
    }
}

