package com.hr.dao.interf;

import com.hr.bean.Training;

import java.util.List;

public interface TrainingDao {
    List<Training> queryAllBeforeCurrentTime();

    Training queryById(Integer id);

    void update(Training training);

    Integer insert(Training training);
}
