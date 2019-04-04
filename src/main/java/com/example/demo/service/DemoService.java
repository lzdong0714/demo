package com.example.demo.service;

import com.example.demo.entity.DemoEntity;
import com.example.demo.entity.Friend;
import com.example.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    /**
     *
     * @param id
     * @return
     */
    public DemoEntity selectById(Integer id){
        return demoMapper.selectById(id);
    }

    @Transactional
    public boolean add(DemoEntity demoEntity){
        Integer id = demoEntity.getId();
        DemoEntity tmp = demoMapper.selectById(id);
        boolean flag = false;
        if(tmp == null){demoMapper.insert(demoEntity);}
        try {
            demoMapper.Update(demoEntity);
            flag = true;
        } catch (Exception e)
        {
            e.printStackTrace();
            //回滚事务
        }
        return flag;
    }

    @Transactional
    public boolean update(DemoEntity demoEntity){
        boolean flag = false;
        try {
            demoMapper.Update(demoEntity);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
        }
        return flag;
    }

    @Transactional
    public boolean delete(Integer id){
        boolean flag = false;
        try {
            demoMapper.deleteBoy(id);
            flag = true;
        }catch (Exception e ){
            e.printStackTrace();
        }
        return  flag;
    }

    //多结果查询
    @Transactional
    public List<DemoEntity> getListOfAdult(Integer age){
        return demoMapper.getAdultMan(age);
    }
}
