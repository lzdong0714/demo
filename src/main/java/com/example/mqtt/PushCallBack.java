package com.example.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallBack implements MqttCallback {


    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("lost connect,please restart");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("arrived topic :" + s);
        System.out.println("接受消息Qos ："+mqttMessage.getQos());
        System.out.println("接受消息内容 ："+new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete----------"+iMqttDeliveryToken.isComplete());

    }
}
