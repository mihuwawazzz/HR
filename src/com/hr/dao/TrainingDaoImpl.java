package com.hr.dao;

import com.hr.bean.Training;
import com.hr.dao.interf.TrainingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("trainingDao")
public class TrainingDaoImpl implements TrainingDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Training> queryAllBeforeCurrentTime() {
        Date date = new Date();
        return (List<Training>) hibernateTemplate
                .find("from Training where beginDate > ?", date);
    }

    @Override
    public Training queryById(Integer id) {
        return hibernateTemplate.get(Training.class, id);
    }

    @Override
    public void update(Training training) {
        hibernateTemplate.update(training);
    }

    @Override
    public Integer insert(Training training) {
        return (Integer) hibernateTemplate.save(training);
    }
}
