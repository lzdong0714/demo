package com.example.demo.mapper;

import com.example.demo.entity.Friend;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FriendMapper {

    @Select("select id from boy where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "girl",column = "id", one = @One(select = "com.example.demo.mapper.GirlMapper.selectById")),
            @Result(property = "boy" ,column = "id", one =@One(select = "com.example.demo.mapper.DemoMapper.selectById")),
    })
    Friend joinFriendById(Integer id);
}
