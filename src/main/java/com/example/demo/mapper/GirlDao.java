package com.example.demo.mapper;

import com.example.demo.entity.Girl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;


public interface GirlDao {
    int insertGirl( Girl girl);

    void Update(Girl girl);

    Girl selectById(@Param("id") Integer id);
}
