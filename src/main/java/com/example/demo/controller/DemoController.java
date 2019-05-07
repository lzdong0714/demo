package com.example.demo.controller;

import com.example.demo.entity.DemoEntity;
import com.example.demo.entity.Girl;
import com.example.demo.service.DemoService;
import com.example.demo.service.GirlService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String Say(){
        return "Fuck World";
    }

    //查
    @RequestMapping(value = "/boy/{id}",method = RequestMethod.GET)
    public DemoEntity findBoy(@PathVariable("id") Integer id){
        return demoService.selectById(id);
    }


    @Autowired
    private GirlService girlService;
    @RequestMapping(value = "/girl/{id}",method = RequestMethod.GET)
    public Girl findGirl(@PathVariable("id") Integer id){
        return girlService.selectGirlById(id);
    }

    //增
    @PostMapping(value = "/addBoy")
    public boolean addBoy(@RequestParam("name") String name, @RequestParam("age") Integer age){
        DemoEntity boyEntity = new DemoEntity();
        boyEntity.setAge(age);
        boyEntity.setName(name);
        boolean flag = demoService.add(boyEntity);
        return flag;
    }

    //改
    @PutMapping(value = "/boy/{id}")
    public boolean updata(@PathVariable("id") Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age){
        DemoEntity boyEntity = new DemoEntity();
        boyEntity.setName(name);
        boyEntity.setAge(age);
        boyEntity.setId(id);

        return demoService.update(boyEntity);
    }

    //删
    @DeleteMapping(value = "/boy/{id}")
    public String deleteBoy(@PathVariable("id") Integer id){
        boolean flag = demoService.delete(id);
        return  flag ?"delete the piss boy":"fail to kill the damon boy";
    }
}
