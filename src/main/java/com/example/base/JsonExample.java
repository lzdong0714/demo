package com.example.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonExample {
    public static void main(String[] args) {
        JsonExample example = new JsonExample();
        example.parse();
    }

    private String jsonStr = "{\n" +
            "\n" +
            "\"name\": \"lims_instrument_correct_expression_lzd\", \n" +
            "\"description\": \"获取仪器检定信息\",\n" +
            "\"mode\": \"simple\",      \n" +
            "\"run_mode\": \"period\", \n" +
            "\"category\": \"instrument_correct\",\n" +
            "\"cron_entry\": \"0 0/3 * * * ? \" ,\n" +
            "\"data_fields\":[\"assigName\",\"department\",\"lastInspectTime\", \"currentDate\",\"nextInspectTime\"],\n" +
            "\"data_format\": \"json\",  \n" +
            "\"fetcher_type\":   \"api\",\n" +
            "\"fetcher_target\": \"http://47.92.28.186:8003/hnty/lims/v1/repo/alarm_origin_info/device\",\n" +
            "\"fetcher_mappings\": \"mappings\"  ,\n" +
            "\"filter\":         \"taskId>0 && taskEndTime<^{now}\", \n" +
            "\n" +
            "\"analyzer_type\": \"qlexpress\",\n" +
            "\"expression\":   \"时间比较(时间转换(nextInspectTime), 现在) > 15\",\n" +
            "\n" +
            "\"transmitter_type\": \"stomp\",\n" +
            "\"wrapper\": \"尊敬的{subscriptions}，{assigName}上次校准日期为{lastInspectTime}，即将于{nextInspectTime}到期,请及时进行校准\",   \n" +
            "\"subscription\": \n" +
            "  [\n" +
            "  \t\"/alert/warn\"\n" +
            "  ]\n" +
            "  \n" +
            "}";

    private String ruleUri = "doc";

    public void parse(){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String name = jsonObject.getString("name");
        File file = new File(ruleUri+"/"+name);
        List<String> data =  (List<String>)jsonObject.get("data_fields");
        Yaml yaml = new Yaml();
        String dumpAsMap = yaml.dumpAsMap(jsonObject);
        try {
            (new BufferedWriter(new FileWriter(file))).write(dumpAsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
