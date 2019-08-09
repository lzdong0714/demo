package com.example.demo.mapper;

import com.example.demo.entity.ViewEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Liu Zhendong
 * @Description 视图查询的demo
 * @createTime 2019年08月09日 11:16:00
 */
@Mapper
public interface ViewMapper {

    List<ViewEntity> getViewItems();
}
