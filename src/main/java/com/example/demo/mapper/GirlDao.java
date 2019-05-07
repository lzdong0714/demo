package com.example.demo.mapper;

import com.example.demo.entity.Girl;
import org.apache.ibatis.annotations.Param;


public interface GirlDao {

    void insert(Girl girl);

    void Update(Girl girl);

    Girl selectById(@Param("id") Integer id);
}
