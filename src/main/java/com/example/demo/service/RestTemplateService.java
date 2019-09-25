package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;
    String url_1 = "http://localhost:8003/hnty/lims/v1/repo/alarm_origin_info/liyuan/late_order";
    String url_2 = "http://localhost:8003/hnty/lims/v1/repo/alarm_origin_info/liyuan/late_order_deliver";
    String url_3 = "\"http://120.78.207.98:8003//hnty/lims/v1/repo/alarm_origin_info/device\"";
    public void getRestTemplate(){
        JsonNode response = restTemplate.
                getForObject(url_2,JsonNode.class);
        System.out.println(response);
    }
}
