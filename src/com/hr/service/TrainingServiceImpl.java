package com.hr.service;

import com.hr.bean.Training;
import com.hr.dao.interf.TrainingDao;
import com.hr.service.interf.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    @Qualifier("trainingDao")
    private TrainingDao trainingDao;

    @Override
    public List<Training> queryAllBeforeCurrentTime() {
        return trainingDao.queryAllBeforeCurrentTime();
    }

    @Override
    public Training queryById(Integer id) {
        return trainingDao.queryById(id);
    }

    @Override
    public void update(Training training) {
        trainingDao.update(training);
    }

    @Override
    public Integer insert(Training training) {
        return trainingDao.insert(training);
    }
}
