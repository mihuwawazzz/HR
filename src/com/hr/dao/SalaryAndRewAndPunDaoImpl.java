package com.hr.dao;

import com.hr.bean.RewardAndPunishment;
import com.hr.dao.interf.SalaryAndRewAndPunDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("salaryAndRewAndPunDao")
public class SalaryAndRewAndPunDaoImpl implements SalaryAndRewAndPunDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public void saveOrUpdateRewardAndPunishment(RewardAndPunishment rewardAndPunishment) {
        hibernateTemplate.saveOrUpdate(rewardAndPunishment);
    }
}
