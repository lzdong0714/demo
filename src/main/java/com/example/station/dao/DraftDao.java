package com.example.station.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DraftDao {

    List<String> test(double left, double right, double bottom, double upside);
}
