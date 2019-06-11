package com.example.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonTest_1 {

    public String jsonStr="[{\"subscriptions\":[\"18772316127\"],\"taskID\":\"TR18110901\",\"projectName\":\"测试\"," +
            "\"clientName\":\"阿克苏诺贝尔特种化学（上海）有限公司\",\"registerDate\":\"2018-11-09 17:06:39\"," +
            "\"planDate\":\"2018-11-10 00:00:00\",\"$时间差(planDate)\":\"208\"}]";

    public void test(){
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        JsonNode arrNode;

    }
}
