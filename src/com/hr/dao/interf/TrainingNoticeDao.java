package com.hr.dao.interf;

import com.hr.bean.Training;
import com.hr.bean.TrainingNotice;

import java.util.List;

public interface TrainingNoticeDao {
    void insertOrUpdate(TrainingNotice trainingNotice);

    List<TrainingNotice> queryByTraining(Training training);

    List<TrainingNotice> queryByUserIdFetch(Integer userId);

    List<TrainingNotice> queryByUserId(Integer userId);

    void updateState(Integer id);
}
