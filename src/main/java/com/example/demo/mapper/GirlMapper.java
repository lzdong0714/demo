package com.example.demo.mapper;

import com.example.demo.entity.DemoEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GirlMapper  {
    //用同一个Entity
    @Insert("insert into girl(id,name,age) values(#{id},#{name},#{age})")
    void insert(DemoEntity demoEntity);

    //改
    @Update("update girl set name=#{name},age=#{age} where id=#{id}")
    void Update(DemoEntity demoEntity);

    //查
    @Select("select id,name,age from girl where id=#{id}")
    DemoEntity selectById(@Param("id") Integer id);

    //删
    @Delete("delete from girl where id=#{id}")
    void deleteGirl(Integer id);

    @Select("select id ,name,age from girl where age>=#{adultAge}")
    List<DemoEntity> getAdultWoman(Integer adultAge);

//    @Select("")

}
