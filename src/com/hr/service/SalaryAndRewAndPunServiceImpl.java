package com.hr.service;

import com.hr.bean.RewardAndPunishment;
import com.hr.dao.interf.SalaryAndRewAndPunDao;
import com.hr.service.interf.SalaryAndRewAndPunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("salaryAndRewAndPunService")
public class SalaryAndRewAndPunServiceImpl implements SalaryAndRewAndPunService{
    @Autowired
    @Qualifier("salaryAndRewAndPunDao")
    private SalaryAndRewAndPunDao salaryAndRewAndPunDao;

    @Override
    public void saveOrUpdateRewardAndPunishment(RewardAndPunishment rewardAndPunishment) {
        salaryAndRewAndPunDao.saveOrUpdateRewardAndPunishment(rewardAndPunishment);
    }
}
