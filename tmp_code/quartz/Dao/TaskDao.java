package com.example.quartz.Dao;

import com.example.quartz.entity.TaskEntity;

import java.util.List;

public interface TaskDao {
    public List<TaskEntity> getTaskEntity();
    public List<TaskEntity> findTaskList(TaskEntity taskEntity);
}
