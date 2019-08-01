package com.example.stomp.entity;

/**
 * @Author: Liu Zhendong
 * @Description 服务端发送的消息实体
 * @createTime 2019年07月31日 17:16:00
 */
public class ServerMessage {
    private String responseMessage;

    public ServerMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
