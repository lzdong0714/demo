package com.example.demo.controller;

import com.example.demo.entity.DemoEntity;
import com.example.demo.entity.Friend;
import com.example.demo.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/join")
public class TableJoinController {

    @Autowired
    public GirlService girlService;

    @RequestMapping(value = "/girl/{id}",method = RequestMethod.GET)
    public DemoEntity getGirl(@PathVariable("id") Integer id){
        return girlService.selectById(id);
    }

    @RequestMapping(value = "/woman/{age}",method = RequestMethod.GET)
    public List<DemoEntity> getWoman(@PathVariable("age") Integer age){
        List<DemoEntity> listWoman  = girlService.getListOfAdult(age);
        return listWoman;
    }

    @RequestMapping(value = "/friend/{id}",method = RequestMethod.GET)
    public Friend getFriendById(@PathVariable("id") Integer id){
        Friend friend = girlService.getFriendById(id);

        return friend;
    }

}
