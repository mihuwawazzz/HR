package com.hr.service.interf;

import com.hr.bean.Training;

import java.util.List;

public interface TrainingService {
    List<Training> queryAllBeforeCurrentTime();

    Training queryById(Integer id);

    void update(Training training);

    Integer insert(Training training);
}
