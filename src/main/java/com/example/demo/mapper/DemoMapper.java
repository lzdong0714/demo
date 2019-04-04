package com.example.demo.mapper;

import com.example.demo.entity.DemoEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DemoMapper {
    //增
    @Insert("insert into boy(id,name,age) values(#{id},#{name},#{age})")
    void insert(DemoEntity demoEntity);

    //改
    @Update("update boy set name=#{name},age=#{age} where id=#{id}")
    void Update(DemoEntity demoEntity);

    //查
    @Select("select id,name,age from boy where id=#{id}")
    DemoEntity selectById(@Param("id") Integer id);

    //删
    @Delete("delete from boy where id=#{id}")
    void deleteBoy(Integer id);

    @Select("select id ,name,age from boy where age>=#{adultAge}")
    List<DemoEntity> getAdultMan(Integer adultAge);

}
