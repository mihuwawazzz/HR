package com.hr.service;

import com.hr.bean.Training;
import com.hr.bean.TrainingNotice;
import com.hr.dao.interf.TrainingNoticeDao;
import com.hr.service.interf.TrainingNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("trainingNoticeService")
public class TrainingNoticeServiceImpl implements TrainingNoticeService {
    @Autowired
    @Qualifier("trainingNoticeDao")
    private TrainingNoticeDao trainingNoticeDao;

    @Override
    public void insertOrUpdate(TrainingNotice trainingNotice) {
        trainingNoticeDao.insertOrUpdate(trainingNotice);
    }

    @Override
    public List<TrainingNotice> queryByTraining(Training training) {
        return trainingNoticeDao.queryByTraining(training);
    }

    @Override
    public List<TrainingNotice> queryByUserIdFetch(Integer userId) {
        return trainingNoticeDao.queryByUserIdFetch(userId);
    }

    @Override
    public List<TrainingNotice> queryByUserId(Integer userId) {
        return trainingNoticeDao.queryByUserId(userId);
    }

    @Override
    public void updateState(Integer id) {
        trainingNoticeDao.updateState(id);
    }
}
