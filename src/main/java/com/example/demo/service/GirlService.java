package com.example.demo.service;

import com.example.demo.entity.DemoEntity;
import com.example.demo.entity.Friend;
import com.example.demo.entity.Girl;
import com.example.demo.mapper.FriendMapper;
import com.example.demo.mapper.GirlDao;
import com.example.demo.mapper.GirlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GirlService {
    @Autowired
    public GirlMapper girlMapper;

    @Autowired
    public FriendMapper friendMapper;

    public DemoEntity selectById(Integer id){
        return girlMapper.selectById(id);
    }

    @Autowired
    public GirlDao girlDao;
    public Girl selectGirlById(Integer id){
        return girlDao.selectById(id);
    }

    @Transactional
    public boolean add(DemoEntity demoEntity){
        Integer id = demoEntity.getId();
        DemoEntity tmp = girlMapper.selectById(id);
        boolean flag = false;
        if(tmp == null){girlMapper.insert(demoEntity);}
        try {
            girlMapper.Update(demoEntity);
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
            girlMapper.Update(demoEntity);
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
            girlMapper.deleteGirl(id);
            flag = true;
        }catch (Exception e ){
            e.printStackTrace();
        }
        return  flag;
    }

    //多结果查询
    @Transactional
    public List<DemoEntity> getListOfAdult(Integer age){
        return girlMapper.getAdultWoman(age);
    }

    //联表查询
    public  Friend getFriendById(Integer id){
        return friendMapper.joinFriendById(id);
    }

}
