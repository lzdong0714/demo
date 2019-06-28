package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    public void getRestTemplate(){
        JsonNode response = restTemplate.
                getForObject("http://120.78.207.98:8003//hnty/lims/v1/repo/alarm_origin_info/device",JsonNode.class);
        System.out.println(response);
    }
}
