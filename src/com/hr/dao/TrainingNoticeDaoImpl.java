package com.hr.dao;

import com.hr.bean.Training;
import com.hr.bean.TrainingNotice;
import com.hr.dao.interf.TrainingNoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("trainingNoticeDao")
public class TrainingNoticeDaoImpl implements TrainingNoticeDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public void insertOrUpdate(TrainingNotice trainingNotice) {
        hibernateTemplate.saveOrUpdate(trainingNotice);
    }

    @Override
    public List<TrainingNotice> queryByTraining(Training training) {
        return (List<TrainingNotice>) hibernateTemplate
                .find("from TrainingNotice where training = ?", training);
    }

    @Override
    public List<TrainingNotice> queryByUserIdFetch(Integer userId) {
        Date date = new Date();
        return (List<TrainingNotice>) hibernateTemplate
                .find("select distinct t from TrainingNotice t inner join fetch t.training where t.userId=? and t.training.beginDate >?", userId, date);
    }

    @Override
    public List<TrainingNotice> queryByUserId(Integer userId) {
        return (List<TrainingNotice>) hibernateTemplate
                .find("from TrainingNotice where userId=?", userId);
    }

    @Override
    public void updateState(Integer id) {
        TrainingNotice trainingNotice = hibernateTemplate.get(TrainingNotice.class, id);
        if (trainingNotice != null) {
            trainingNotice.setState(TrainingNotice.CHECK);
            hibernateTemplate.update(trainingNotice);
        }
    }
}
