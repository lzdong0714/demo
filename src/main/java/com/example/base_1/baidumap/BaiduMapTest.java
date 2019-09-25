package com.example.base_1.baidumap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年09月02日 17:00:00
 */
public class BaiduMapTest {
    public static Map<String, String> getAddressByLngLat(double longitude , double latitude){
        String baiduMapUrl = "http://api.map.baidu.com/geocoder/v2/?ak=wNlMc43xEUvwCIwsyelE7mDRm81CzOR1&location="+latitude+","+longitude+"&output=json&pois=1";
        RestTemplate send = new RestTemplate();
        String json=send.getForObject(baiduMapUrl, String.class);
        JSONObject jsonObject=null;
        JSONObject jsonObject1=null;
        Map<String, String> positionMap=new HashMap<String, String>();
        try {
            JSONObject result1 = JSON
                    .parseObject(json)
                    .getJSONObject("result");
//                    .getInstance().readTree(json).get("result");
            jsonObject=result1.getJSONObject("addressComponent");
            positionMap.put("city", jsonObject.getString("city"));
            positionMap.put("district", jsonObject.get("district").toString());
            positionMap.put("street", jsonObject.get("street").toString());
            positionMap.put("streetNumber", jsonObject.get("street_number").toString());
            positionMap.put("positionCode", jsonObject.get("adcode").toString());
            positionMap.put("address", result1.get("formatted_address").toString());
            return positionMap;
        } catch (Exception e) {
            e.printStackTrace();
            return positionMap;
        }
    }


    public static void main(String[] args) {
        test_1();
    }

    private static void test_1(){
        Map<String, String> addressByLngLat = BaiduMapTest.getAddressByLngLat(120.237037, 36.252892);
        addressByLngLat.keySet().forEach(item->{
            System.out.println(item+" : "+addressByLngLat.get(item));
        });
    }
}
